package com.intelligentLibrary.rfid;

import javax.swing.ButtonGroup;
import javax.swing.JTextField;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.intelligentLibrary.dev.Dao.AdminManageBookDAO;
import com.intelligentLibrary.rfid.MifareOneDemo.ReaderLib;
import com.sun.jna.Native;
import com.sun.jna.win32.StdCallLibrary;

public class SendRequest {

	int g_hDevice[] = new int[] { -1 }; // hDevice must init as -1
	private JTextField textUID;
	private JTextField textKey;
	private JTextField textData;
	private final ButtonGroup buttonGroup = new ButtonGroup();


	// Load DLL Library
	public interface ReaderLib extends StdCallLibrary {
		ReaderLib INSTANCE = (ReaderLib) Native.loadLibrary("hfrdapi", ReaderLib.class);

		int Sys_Open(int[] device, int index, short vid, short pid);

		int Sys_Close(int[] device);

		boolean Sys_IsOpen(int device);

		int Sys_SetLight(int device, byte color);

		int Sys_SetBuzzer(int device, byte msec);

		int Sys_SetAntenna(int device, byte mode);

		int Sys_InitType(int device, byte type);

		int TyA_Request(int device, byte mode, short[] pTagType);

		int TyA_Anticollision(int device, byte bcnt, byte[] pSnr, byte[] pLen);

		int TyA_Select(int device, byte[] pSnr, byte snrLen, byte[] pSak);

		int TyA_Halt(int device);

		int TyA_CS_Authentication2(int device, byte mode, byte block, byte[] pKey);

		int TyA_CS_Read(int device, byte block, byte[] pData, byte[] pLen);

		int TyA_CS_Write(int device, byte block, byte[] pData);

		int TyA_UID_Write(int device, byte[] pData);
	}

	public String ConnectToDevice() {
		int status;
		boolean bStatus;

		// =================== Connect the reader ===================
		// Check whether the reader is connected or not
		// If the reader is already open , close it firstly
		bStatus = ReaderLib.INSTANCE.Sys_IsOpen(g_hDevice[0]);
		if (bStatus == true) {
			status = ReaderLib.INSTANCE.Sys_Close(g_hDevice);
			if (status != 0) {
				System.out.println("Sys_Close failed !");
				return "Sys_Close failed !";
			}
		}

		// Connect
		status = ReaderLib.INSTANCE.Sys_Open(g_hDevice, 0, (short) 0x0416, (short) 0x8020);
		if (status != 0) {
			System.out.println("Sys_Open failed !");
			return "Sys_Open failed !";
		}

		// ========== Init the reader before operating the card ==========
		// Close antenna of the reader
		status = ReaderLib.INSTANCE.Sys_SetAntenna(g_hDevice[0], (byte) 0);
		if (status != 0) {
			System.out.println("Sys_SetAntenna failed !");
			return "Sys_SetAntenna failed !";
		}
		// Appropriate delay after Sys_SetAntenna operating
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
		}

		// Set the reader's working mode
		status = ReaderLib.INSTANCE.Sys_InitType(g_hDevice[0], (byte) 'A');
		if (status != 0) {
			System.out.println("Sys_InitType failed !");
			return "Sys_InitType failed !";
		}
		// Appropriate delay after Sys_SetAntenna operating
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
		}

		// Open antenna of the reader
		status = ReaderLib.INSTANCE.Sys_SetAntenna(g_hDevice[0], (byte) 1);
		if (status != 0) {
			System.out.println("Sys_SetAntenna failed !");
			return "Sys_SetAntenna failed !";
		}
		// Appropriate delay after Sys_SetAntenna operating
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
		}

		// ==================== Success Tips ====================
		// Beep 200 ms
		status = ReaderLib.INSTANCE.Sys_SetBuzzer(g_hDevice[0], (byte) 20);
		if (status != 0) {
			System.out.println("Sys_SetBuzzer failed !");
			return "Sys_SetBuzzer failed !";
		}

		// Tips
		return "Connect reader succeed !";
	}

	public void Everyfiveseconds() {
		System.out.println("-------------------------------");
		int status;
		byte mode = 0x52;
		short[] TagType = new short[1];
		byte bcnt = 0;
		byte snr[] = new byte[16];
		byte len[] = new byte[1];
		byte sak[] = new byte[1];

		// Check whether the reader is connected or not
		if (false == ReaderLib.INSTANCE.Sys_IsOpen(g_hDevice[0])) {
			System.out.println("Please connect the device firstly !");
			ConnectToDevice();
			if ("Connect reader succeed !" == ConnectToDevice()) {
				System.out.println("成功链接到设备");
			} else {
				System.out.println("---------connection can't be established");
			}
		}

		// Request card
		status = ReaderLib.INSTANCE.TyA_Request(g_hDevice[0], mode, TagType);// search
																				// all
																				// card
		if (status != 0) {
			System.out.println("TyA_Request failed !");
			return;
		}

		// Anticollision
		status = ReaderLib.INSTANCE.TyA_Anticollision(g_hDevice[0], bcnt, snr, len);// return
																					// serial
																					// number
																					// of
																					// card
		if (status != 0 || len[0] != 4) {
			System.out.println("TyA_Anticollision failed !");
			return;
		}

		String str = "";
		for (int i = 0; i < 4; i++) {
			str = str + String.format("%02X", snr[i]);
		}
		textUID.setText(str);

		// Select card
		status = ReaderLib.INSTANCE.TyA_Select(g_hDevice[0], snr, len[0], sak);// lock
																				// ISO14443-3
																				// TYPE_A
		if (status != 0) {
			System.out.println("TyA_Select failed !");
			return;
		}

		// Tips
		System.out.println("Request card succeed !");

	}

}
