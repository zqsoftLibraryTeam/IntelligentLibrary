package com.intelligentLibrary.rfid;

public class TransCodeUtil {
	/**
	 * RFID16浣�16杩涘埗鏁拌浆鏁村瀷
	 * @param str
	 * @return
	 */
	public static int HexToInteger(String str)
	{
		String readytotrans=str.substring(str.lastIndexOf("0")+1);
		return Integer.parseInt(readytotrans,16);
	}

}
