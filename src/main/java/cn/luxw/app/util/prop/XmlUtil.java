//package cn.luxw.app.util.prop;
//
//import java.io.InputStream;
//import java.io.Writer;
//import java.util.List;
//import java.util.Map;
//
//import org.dom4j.Document;
//import org.dom4j.DocumentException;
//import org.dom4j.Element;
//import org.dom4j.io.SAXReader;
//
//import weixin.entity.message.response.Article;
//import weixin.entity.message.response.NewsMessage;
//
//
//import com.google.common.collect.Maps;
//import com.thoughtworks.xstream.XStream;
//import com.thoughtworks.xstream.core.util.QuickWriter;
//import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
//import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
//import com.thoughtworks.xstream.io.xml.XppDriver;
//
///**
// * xml工具
// * @author Luxh
// *
// */
//public class XmlUtil {
//	
//	
//	private final static String PREFIX_CDATA = "<![CDATA[";   
//	private final static String SUFFIX_CDATA = "]]>";   
//	
//	/**
//	 * 从输入流中解析xml
//	 * @param in
//	 * @return
//	 * @throws DocumentException
//	 */
//	@SuppressWarnings("unchecked")
//	public static Map<String,String> parseXmlFormInputStream(InputStream in) throws DocumentException{
//		Map<String,String> resultMap = Maps.newHashMap();
//		SAXReader reader = new SAXReader(); 
//		Document document = reader.read(in);  
//	    Element root = document.getRootElement();  
//	    List<Element> elementList = root.elements();  
//	    for(Element el : elementList){
//	    	resultMap.put(el.getName(), el.getText()); 
//	    }
//	    return resultMap;
//	}
//	
//	
//	/**
//	 * 对象转为xml
//	 * @param textMessage
//	 * @return
//	 */
//	public static String message2Xml(Object obj) {  
//	    xstream.alias("xml",obj.getClass());  
//	    return xstream.toXML(obj);  
//	}  
//	
//	/**
//	 * 图文消息 to xml
//	 * @param newsMessage
//	 * @return
//	 */
//	public static String newsMessageToXml(NewsMessage newsMessage) {  
//	    xstream.alias("xml", newsMessage.getClass());  
//	    xstream.alias("item", new Article().getClass());  
//	    return xstream.toXML(newsMessage);  
//	}  
//	
//	
//	
//	
//	
//	private static XStream xstream = new XStream(new XppDriver() {  
//	    public HierarchicalStreamWriter createWriter(Writer out) {  
//	        return new PrettyPrintWriter(out) {  
//	            //对所有xml节点的转换都增加CDATA标记  
//	            boolean cdata = true;  
//	            @SuppressWarnings("rawtypes")
//				public void startNode(String name, Class clazz) {  
//	                super.startNode(name, clazz);  
//	            }  
//	  
//	            protected void writeText(QuickWriter writer, String text) {  
//	                if (cdata) {  
//	                    writer.write(PREFIX_CDATA);  
//	                    writer.write(text);  
//	                    writer.write(SUFFIX_CDATA);  
//	                } else {  
//	                    writer.write(text);  
//	                }  
//	            }  
//	        };  
//	    }  
//	});  
//}
