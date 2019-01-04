package cn.luxw.app.util.wx;


import com.thoughtworks.xstream.XStream;

/**
 * XML转换
 * @author luxw
 * @version
 *    1.0
 */
public class XmlUtil {

//	private static final String PREFIX_CDATA = "<![CDATA[";
//	private static final String SUFFIX_CDATA = "]]>";
	

	/**
	 * 对象转为xml
	 * 
	 * @param textMessage
	 * @return
	 
	public static String objectToXml(Object obj) {
		xstream.alias(ALIAS_XML, obj.getClass());
		return xstream.toXML(obj);
	}
	*/
	
	public static String toXML(Object xmlObj){
		XStream xstream = null;//new XStream(new DomDriver(Charsets.UTF_8.name(), new XmlFriendlyNameCoder("-_", "_")));
		//xstream.alias(Const.ALIAS_XML, xmlObj.getClass());
		String xmlStr = xstream.toXML(xmlObj);
		return xmlStr;
	}
	

	@SuppressWarnings("unchecked")
	public static <T> T getObjectFromXML(String xml, Class<T> clazz) {
		XStream xstream = new XStream();
		//xstream.alias(Const.ALIAS_XML, clazz);
		//xstream.ignoreUnknownElements();
		return (T)xstream.fromXML(xml);
	}

	/*暂时不用
	private static XStream xstream = new XStream(new XppDriver() {
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out) {
				// 对所有xml节点的转换都增加CDATA标记
				boolean cdata = true;

				@SuppressWarnings("rawtypes")
				public void startNode(String name, Class clazz) {
					super.startNode(name, clazz);
				}

				protected void writeText(QuickWriter writer, String text) {
					if (cdata) {
						writer.write(PREFIX_CDATA);
						writer.write(text);
						writer.write(SUFFIX_CDATA);
					} else {
						writer.write(text);
					}
				}
			};
		}
	});
	*/
}
