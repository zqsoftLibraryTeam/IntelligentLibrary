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
 * Apriori算法实现 最大模式挖掘，涉及到支持度
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
	
	private static final double MIN_SUPPROT = 0.2;//最小支持度
	private static boolean endTag = false;//循环状态
	static List<List<String>> record = new ArrayList<List<String>>();//数据集
	
	/*public static void main(String args[]){
		//*************读取数据集**************
		record = getRecord();
		//控制台输出记录
		//************获取候选1项集**************
		List<List<String>> CandidateItemset = findFirstCandidate();
		
		//************获取频繁1项集***************
		List<List<String>> FrequentItemset = getSupprotedItemset(CandidateItemset);
		
		//***************迭代过程**************
		while(endTag!=true){
			//**********连接操作****由k-1项频繁集      获取      候选k项集**************
			List<List<String>> nextCandidateItemset = getNextCandidate(FrequentItemset);
			
			//**************减枝操作***由候选k项集       获取     频繁k项集****************
			List<List<String>> nextFrequentItemset = getSupprotedItemset(nextCandidateItemset);
			//*********如果循环结束，输出最大模式**************
			if(endTag == true){
				System.out.println("Apriori算法--->频繁集");
				for(int i=0;i<FrequentItemset.size();i++){
					List<String> list = new ArrayList<String>(FrequentItemset.get(i));
					for(int j=0;j<list.size();j++){
						System.out.print(list.get(j)+" ");
					}
					System.out.println();
				}
			}
			//****************下一次循环初值********************
			CandidateItemset = nextCandidateItemset;
			FrequentItemset = nextFrequentItemset;
		}
		
	}*/
	
	
	public Map<String,String> MakingRules()
	{
		//计算频繁项集每个项的置信度
		List<List<String>> frequentItemSet=SupportCalculate();
		//生成每个项
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
						//计算置信度,阀值设置为0.667
						if(calculateConfidence(lists.get(i), lists.get(j))>=0.667)
						{
							//生成规则
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
		//*************读取数据集**************
		record = getRecord();
		//控制台输出记录
		//************获取候选1项集**************
		List<List<String>> CandidateItemset = findFirstCandidate();
		
		//************获取频繁1项集***************
		List<List<String>> FrequentItemset = getSupprotedItemset(CandidateItemset);
		
		//***************迭代过程**************
		while(endTag!=true){
			//**********连接操作****由k-1项频繁集      获取      候选k项集**************
			List<List<String>> nextCandidateItemset = getNextCandidate(FrequentItemset);
			
			//**************减枝操作***由候选k项集       获取     频繁k项集****************
			List<List<String>> nextFrequentItemset = getSupprotedItemset(nextCandidateItemset);
			//*********如果循环结束，输出最大模式**************
			//****************下一次循环初值********************
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
	 * 读取数据
	 * @return
	 */
	public static List<List<String>> getRecord() {
		List<List<String>> record = new ArrayList<List<String>>();
		try {
			String encoding = "GBK"; // 字符编码(可解决中文乱码问题 )
			File file = new File("D:/干他妈的RFID/干他妈的工作空间/MainLibrary/sample.txt");
			if (file.isFile() && file.exists()) {
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTXT = null;
				while ((lineTXT = bufferedReader.readLine()) != null) {// 读一行文件
					String[] lineString = lineTXT.split(" ");
					List<String> lineList = new ArrayList<String>();
					for (int i = 0; i < lineString.length; i++) {// 处理矩阵中的T、F、YES、NO
						if (lineString[i].endsWith("T") || lineString[i].endsWith("YES"))
							lineList.add(record.get(0).get(i));
						else if (lineString[i].endsWith("F") || lineString[i].endsWith("NO"))
							;// F，NO记录不保存
						else
							lineList.add(lineString[i]);
					}
					record.add(lineList);
				}
				read.close();
			} else {
				System.out.println("找不到指定的文件！");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容操作出错");
			e.printStackTrace();
		}
		return record;
	}
	
	/**
	 * 有当前频繁项集自连接求下一次候选集
	 * @param FrequentItemset
	 * @return
	 */
	private static List<List<String>> getNextCandidate(List<List<String>> FrequentItemset) {
		List<List<String>> nextCandidateItemset = new ArrayList<List<String>>();
		for (int i=0; i<FrequentItemset.size(); i++){
			
			HashSet<String> hsSet = new HashSet<String>();
			HashSet<String> hsSettemp = new HashSet<String>();
			for (int k=0; k< FrequentItemset.get(i).size(); k++)//获得频繁集第i行
				hsSet.add(FrequentItemset.get(i).get(k));
			int hsLength_before = hsSet.size();//添加前长度
			hsSettemp=(HashSet<String>) hsSet.clone();
			for(int h=i+1; h<FrequentItemset.size(); h++){//频繁集第i行与第j行(j>i)连接   每次添加且添加一个元素组成    新的频繁项集的某一行，   
				hsSet=(HashSet<String>) hsSettemp.clone();//！！！做连接的hasSet保持不变
				for(int j=0; j< FrequentItemset.get(h).size();j++)
					hsSet.add(FrequentItemset.get(h).get(j));
				int hsLength_after = hsSet.size();			
				if(hsLength_before+1 == hsLength_after && isSubsetOf(hsSet,record)==1 && isnotHave(hsSet,nextCandidateItemset)){
					//如果不相等，表示添加了1个新的元素，再判断其是否为record某一行的子集     若是则其为  候选集中的一项
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
	 * 判断新添加元素形成的候选集是否在  新的候选集中
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
	 * 判断hsSet是不是record2中的某一记录子集
	 * @param hsSet
	 * @param record2
	 * @return
	 */
	private static int isSubsetOf(HashSet<String> hsSet,
			List<List<String>> record2) {
		//hsSet转换成List
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
	 * 由k项候选集剪枝得到k项频繁集
	 * @param CandidateItemset
	 * @return
	 */
	private static List<List<String>> getSupprotedItemset(List<List<String>> CandidateItemset) {
		// TODO Auto-generated method stub
		boolean end = true;
		List<List<String>> supportedItemset = new ArrayList<List<String>>();
		int k = 0;
		
		for (int i = 0; i < CandidateItemset.size(); i++){
			
			int count = countFrequent(CandidateItemset.get(i));//统计记录数
			
			if (count >= MIN_SUPPROT * (record.size()-1)){	
				supportedItemset.add(CandidateItemset.get(i));
				end = false;
			}
		}
		endTag = end;//存在频繁项集则不会结束
		if(endTag==true)
			System.out.println("无满足支持度项集,结束连接");
		return supportedItemset;
	}

	/**
	 * 统计record中出现list集合的个数
	 * @param list
	 * @return
	 */
	private static int countFrequent(List<String> list) {
		// TODO Auto-generated method stub
		int count = 0;
		for(int i = 1; i<record.size(); i++) {
			
			boolean notHaveThisList = false;
			
			for (int k=0; k < list.size(); k++){//判断record.get(i)是否包含list
				boolean thisRecordHave = false;
				for(int j=1; j<record.get(i).size(); j++){
					if(list.get(k).equals(record.get(i).get(j)))//list。get(k)在record。get(i)中能找到
						thisRecordHave = true;
				}
				if(!thisRecordHave){//只要有一个list元素找不到，则退出其余元素比较,进行下一个record。get(i)比较
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
	 * 获得一项候选集
	 * @return
	 */
	private static List<List<String>> findFirstCandidate() {
		List<List<String>> tableList = new ArrayList<List<String>>();
		HashSet<String> hs  = new HashSet<String>();
		for (int i = 1; i<record.size(); i++){  //第一行为图书信息
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