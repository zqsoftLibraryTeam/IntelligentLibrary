package com.intelligentLibrary.recommand.corearithmetic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.intelligentLibrary.dev.Dao.AdminManageBookDAO;
import com.intelligentLibrary.dev.Dao.RecommandDAO;
import com.intelligentLibrary.dev.Dao.RuleBookDAO;
import com.intelligentLibrary.dev.domain.Book;
import com.intelligentLibrary.dev.domain.BorrowInfo;
import com.intelligentLibrary.dev.domain.User;
import com.intelligentLibrary.dev.service.RecommandService;
import com.intelligentLibrary.dev.service.impl.RecommandServiceImpl;
import com.intelligentLibrary.recommand.entity.RuleBook;

/**
 * Apriori�㷨ʵ�� ���ģʽ�ھ��漰��֧�ֶ�
 * @author push_pop
 *
 */
@Component
public class AprioriArithmetic {
	
	@Resource
	RecommandDAO recommandDAO;
	@Resource
	RuleBookDAO ruleBookDAO;
	@Resource
	AdminManageBookDAO adminManageBookDAO;
	
	private static final double MIN_SUPPROT = 0.2;//��С֧�ֶ�
	private static boolean endTag = false;//ѭ��״̬
	static List<List<String>> record = new ArrayList<List<String>>();//���ݼ�
	
	/*public static void main(String args[]){
		//*************��ȡ���ݼ�**************
		record = getRecord();
		//����̨�����¼
		//************��ȡ��ѡ1�**************
		List<List<String>> CandidateItemset = findFirstCandidate();
		
		//************��ȡƵ��1�***************
		List<List<String>> FrequentItemset = getSupprotedItemset(CandidateItemset);
		
		//***************��������**************
		while(endTag!=true){
			//**********���Ӳ���****��k-1��Ƶ����      ��ȡ      ��ѡk�**************
			List<List<String>> nextCandidateItemset = getNextCandidate(FrequentItemset);
			
			//**************��֦����***�ɺ�ѡk�       ��ȡ     Ƶ��k�****************
			List<List<String>> nextFrequentItemset = getSupprotedItemset(nextCandidateItemset);
			//*********���ѭ��������������ģʽ**************
			if(endTag == true){
				System.out.println("Apriori�㷨--->Ƶ����");
				for(int i=0;i<FrequentItemset.size();i++){
					List<String> list = new ArrayList<String>(FrequentItemset.get(i));
					for(int j=0;j<list.size();j++){
						System.out.print(list.get(j)+" ");
					}
					System.out.println();
				}
			}
			//****************��һ��ѭ����ֵ********************
			CandidateItemset = nextCandidateItemset;
			FrequentItemset = nextFrequentItemset;
		}
		
	}*/
	
	
	public Map<String,String> MakingRules()
	{
		//����Ƶ���ÿ��������Ŷ�
		List<List<String>> frequentItemSet=SupportCalculate();
		//����ÿ����
		Map<String,String> rules=new HashMap<String,String>();
		for(List<String> lists:frequentItemSet)
		{
			for(int i=0;i<lists.size();i++)
			{
				for(int j=i+1;j<lists.size();j++)
				{
					if(i==lists.size()-1)
					{
						break;
					}
					else
					{
						//�������Ŷ�,��ֵ����Ϊ0.667
						if(calculateConfidence(lists.get(i), lists.get(j))>=0.667)
						{
							//���ɹ���
							rules.put(lists.get(i)+"=>"+lists.get(j), lists.get(i)+"=>"+lists.get(j));
							rules.put(lists.get(j)+"=>"+lists.get(i), lists.get(j)+"=>"+lists.get(i));
							Book currentBook=adminManageBookDAO.getBookByBookName(lists.get(i));
							Book targetBook=adminManageBookDAO.getBookByBookName(lists.get(j));
							RuleBook ruleA = new RuleBook();
							ruleA.setCurrentBook(currentBook);
							ruleA.setTargetBook(targetBook);
							ruleBookDAO.save(ruleA);
						}
					}
				}
			}
		}
		return rules;
		
	}
	
	public double calculateConfidence(String target,String condition){
			
		   double result=recommandDAO.ConfidenceCount(target, condition);
		   System.out.println(result);
		   return result;
	}
	
	
	public List<List<String>> SupportCalculate()
	{
		//*************��ȡ���ݼ�**************
		record = getRecord();
		//����̨�����¼
		//************��ȡ��ѡ1�**************
		List<List<String>> CandidateItemset = findFirstCandidate();
		
		//************��ȡƵ��1�***************
		List<List<String>> FrequentItemset = getSupprotedItemset(CandidateItemset);
		
		//***************��������**************
		while(endTag!=true){
			//**********���Ӳ���****��k-1��Ƶ����      ��ȡ      ��ѡk�**************
			List<List<String>> nextCandidateItemset = getNextCandidate(FrequentItemset);
			
			//**************��֦����***�ɺ�ѡk�       ��ȡ     Ƶ��k�****************
			List<List<String>> nextFrequentItemset = getSupprotedItemset(nextCandidateItemset);
			//*********���ѭ��������������ģʽ**************
			//****************��һ��ѭ����ֵ********************
			CandidateItemset = nextCandidateItemset;
			if(nextFrequentItemset==null||nextFrequentItemset.size()==0)
			{
				return FrequentItemset;
			}
			FrequentItemset = nextFrequentItemset; 
		}
		return FrequentItemset;
	}
	
	/**
	 * ��ȡ����
	 * @return
	 */
	public static List<List<String>> getRecord() {
		List<List<String>> record = new ArrayList<List<String>>();
		try {
			String encoding = "GBK"; // �ַ�����(�ɽ�������������� )
			File file = new File("D:/�������RFID/������Ĺ����ռ�/MainLibrary/sample.txt");
			if (file.isFile() && file.exists()) {
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTXT = null;
				while ((lineTXT = bufferedReader.readLine()) != null) {// ��һ���ļ�
					String[] lineString = lineTXT.split(" ");
					List<String> lineList = new ArrayList<String>();
					for (int i = 0; i < lineString.length; i++) {// ��������е�T��F��YES��NO
						if (lineString[i].endsWith("T") || lineString[i].endsWith("YES"))
							lineList.add(record.get(0).get(i));
						else if (lineString[i].endsWith("F") || lineString[i].endsWith("NO"))
							;// F��NO��¼������
						else
							lineList.add(lineString[i]);
					}
					record.add(lineList);
				}
				read.close();
			} else {
				System.out.println("�Ҳ���ָ�����ļ���");
			}
		} catch (Exception e) {
			System.out.println("��ȡ�ļ����ݲ�������");
			e.printStackTrace();
		}
		return record;
	}
	
	/**
	 * �е�ǰƵ�������������һ�κ�ѡ��
	 * @param FrequentItemset
	 * @return
	 */
	private static List<List<String>> getNextCandidate(List<List<String>> FrequentItemset) {
		List<List<String>> nextCandidateItemset = new ArrayList<List<String>>();
		for (int i=0; i<FrequentItemset.size(); i++){
			
			HashSet<String> hsSet = new HashSet<String>();
			HashSet<String> hsSettemp = new HashSet<String>();
			for (int k=0; k< FrequentItemset.get(i).size(); k++)//���Ƶ������i��
				hsSet.add(FrequentItemset.get(i).get(k));
			int hsLength_before = hsSet.size();//���ǰ����
			hsSettemp=(HashSet<String>) hsSet.clone();
			for(int h=i+1; h<FrequentItemset.size(); h++){//Ƶ������i�����j��(j>i)����   ÿ����������һ��Ԫ�����    �µ�Ƶ�����ĳһ�У�   
				hsSet=(HashSet<String>) hsSettemp.clone();//�����������ӵ�hasSet���ֲ���
				for(int j=0; j< FrequentItemset.get(h).size();j++)
					hsSet.add(FrequentItemset.get(h).get(j));
				int hsLength_after = hsSet.size();			
				if(hsLength_before+1 == hsLength_after && isSubsetOf(hsSet,record)==1 && isnotHave(hsSet,nextCandidateItemset)){
					//�������ȣ���ʾ�����1���µ�Ԫ�أ����ж����Ƿ�Ϊrecordĳһ�е��Ӽ�     ��������Ϊ  ��ѡ���е�һ��
					Iterator<String> itr = hsSet.iterator();
					List<String>  tempList = new ArrayList<String>();
					while(itr.hasNext()){
						String Item = (String) itr.next();
						tempList.add(Item);
					}
					nextCandidateItemset.add(tempList);
				}
					
			}
			
		}
		return nextCandidateItemset;
	}
	/**
	 * �ж������Ԫ���γɵĺ�ѡ���Ƿ���  �µĺ�ѡ����
	 * @param hsSet
	 * @param nextCandidateItemset
	 * @return
	 */
	private static boolean isnotHave(HashSet<String> hsSet,
			List<List<String>> nextCandidateItemset) {
		// TODO Auto-generated method stub
		List<String>  tempList = new ArrayList<String>();
		Iterator<String> itr = hsSet.iterator();
		while(itr.hasNext()){
			String Item = (String) itr.next();
			tempList.add(Item);
		}
		for(int i=0; i<nextCandidateItemset.size();i++)
			if(tempList.equals(nextCandidateItemset.get(i)))
				return false;
		return true;
	}

	/**
	 * �ж�hsSet�ǲ���record2�е�ĳһ��¼�Ӽ�
	 * @param hsSet
	 * @param record2
	 * @return
	 */
	private static int isSubsetOf(HashSet<String> hsSet,
			List<List<String>> record2) {
		//hsSetת����List
		List<String>  tempList = new ArrayList<String>();
		Iterator<String> itr = hsSet.iterator();
		while(itr.hasNext()){
			String Item = (String) itr.next();
			tempList.add(Item);
		}		
		
		for(int i=1;i<record.size();i++){
			List<String>  tempListRecord = new ArrayList<String>();
			for(int j=1;j<record.get(i).size();j++)
				tempListRecord.add(record.get(i).get(j));
			if(tempListRecord.containsAll(tempList))
				return 1;
			}
		return 0;
	}

	/**
	 * ��k���ѡ����֦�õ�k��Ƶ����
	 * @param CandidateItemset
	 * @return
	 */
	private static List<List<String>> getSupprotedItemset(List<List<String>> CandidateItemset) {
		// TODO Auto-generated method stub
		boolean end = true;
		List<List<String>> supportedItemset = new ArrayList<List<String>>();
		int k = 0;
		
		for (int i = 0; i < CandidateItemset.size(); i++){
			
			int count = countFrequent(CandidateItemset.get(i));//ͳ�Ƽ�¼��
			
			if (count >= MIN_SUPPROT * (record.size()-1)){	
				supportedItemset.add(CandidateItemset.get(i));
				end = false;
			}
		}
		endTag = end;//����Ƶ����򲻻����
		if(endTag==true)
			System.out.println("������֧�ֶ��,��������");
		return supportedItemset;
	}

	/**
	 * ͳ��record�г���list���ϵĸ���
	 * @param list
	 * @return
	 */
	private static int countFrequent(List<String> list) {
		// TODO Auto-generated method stub
		int count = 0;
		for(int i = 1; i<record.size(); i++) {
			
			boolean notHaveThisList = false;
			
			for (int k=0; k < list.size(); k++){//�ж�record.get(i)�Ƿ����list
				boolean thisRecordHave = false;
				for(int j=1; j<record.get(i).size(); j++){
					if(list.get(k).equals(record.get(i).get(j)))//list��get(k)��record��get(i)�����ҵ�
						thisRecordHave = true;
				}
				if(!thisRecordHave){//ֻҪ��һ��listԪ���Ҳ��������˳�����Ԫ�رȽ�,������һ��record��get(i)�Ƚ�
					notHaveThisList = true;
					break;
				}
			}
			
			if(notHaveThisList == false)
				count++;
			
		}
		return count;
	}
		
	/**
	 * ���һ���ѡ��
	 * @return
	 */
	private static List<List<String>> findFirstCandidate() {
		List<List<String>> tableList = new ArrayList<List<String>>();
		HashSet<String> hs  = new HashSet<String>();
		for (int i = 1; i<record.size(); i++){  //��һ��Ϊͼ����Ϣ
			for(int j=1;j<record.get(i).size();j++){
				hs.add(record.get(i).get(j));
			}
		}	
		Iterator<String> itr = hs.iterator();
		while(itr.hasNext()){
			List<String>  tempList = new ArrayList<String>();
			String Item = (String) itr.next();
			tempList.add(Item);
			tableList.add(tempList);
		}
		return tableList;
	}
}