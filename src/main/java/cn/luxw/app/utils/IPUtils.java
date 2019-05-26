package cn.luxw.app.utils;

public class IPUtils {

	/**
	 * ip字符串转为整数
	 * @param strIp
	 * @return
	 */
     public static long ipToLong(String strIp){
    	 long[] ip = new long[4];
    	 int position1 = strIp.indexOf(".");
    	 int position2 = strIp.indexOf(".", position1 + 1);
    	 int position3 = strIp.indexOf(".", position2 + 1);
         //将每个.之间的字符串转换成整型
         ip[0] = Long.parseLong(strIp.substring(0, position1));
         ip[1] = Long.parseLong(strIp.substring(position1+1, position2));
         ip[2] = Long.parseLong(strIp.substring(position2+1, position3));
         ip[3] = Long.parseLong(strIp.substring(position3+1));
         //通过左移位操作（<<）给每一段的数字加权，第一段的权为2的24次方，第二段的权为2的16次方，第三段的权为2的8次方，最后一段的权为1
         return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
     }
    
     
     /**
      * 
      * 将整数转为IP字符串
      * @param longIp
      * @return
      */
     public static String longToIP(long longIp){
    	 //将整数值进行右移位操作（>>>），右移24位，右移时高位补0，得到的数字即为第一段IP
    	 String p1 = String.valueOf(longIp >>> 24);
    	 //通过与操作符（&）将整数值的高8位设为0，再右移16位，得到的数字即为第二段IP
    	 String p2 = String.valueOf((longIp & 0x00FFFFFF) >>> 16);
    	 //通过与操作符吧整数值的高16位设为0，再右移8位，得到的数字即为第三段IP
    	 String p3 = String.valueOf((longIp & 0x0000FFFF) >>> 8);
    	 //通过与操作符吧整数值的高24位设为0，得到的数字即为第四段IP
    	 String p4 = String.valueOf((longIp & 0x000000FF));
    	 
         StringBuffer sb = new StringBuffer();
         sb.append(p1).append(".").append(p2).append(".").append(p3).append(".").append(p4);
         return sb.toString();
     }
    
     public static void main(String[] args){
         String ipStr = "243.168.255.0";
         long longIp = IPUtils.ipToLong(ipStr);
//         System.out.println("整数形式：" + longIp);
//         System.out.println("字符串形式：" + IPUtils.longToIP(longIp));
         System.out.println(IPUtils.class.getCanonicalName());
     }
 
}