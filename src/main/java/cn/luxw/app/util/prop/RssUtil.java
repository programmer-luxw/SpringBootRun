//package cn.luxw.app.util.prop;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.List;
//
//import org.dom4j.Document;
//import org.dom4j.Element;
//import org.dom4j.io.SAXReader;
//
//import com.google.common.collect.Lists;
//
//import weixin.constant.PropKey;
//import weixin.entity.Rss;
//import weixin.exception.BusinessException;
///**
// * 
// * @author Luxh
// *
// */
//public class RssUtil {
//	
//	@SuppressWarnings("unchecked")
//	public static List<Rss> getRss(){
//		List<Rss> rssList = Lists.newArrayList();
//		InputStream in = HttpUtil.get(PropertiesUtil.get(PropKey.RSS_URL));
//		try{
//			SAXReader reader = new SAXReader();
//			Document document = reader.read(in);  
//			List<Element> els = document.getRootElement().elements();
//			if(els!= null && els.size()>0){
//				List<Element> items = els.get(0).elements("item");
//				if(items!=null && items.size()>0){
//					for(Element item:items){
//						List<Element> fields = item.elements();
//						Rss rss = new Rss();
//						for(Element e:fields){
//							if("title".equals(e.getName())){
//								rss.setTitle(e.getText());
//							}else if("link".equals(e.getName())){
//								rss.setLink(e.getText());
//							}else if("pubDate".equals(e.getName())){
//								rss.setPubDate(DateUtil.format(e.getText(), "yyyy-MM-dd"));
//							}else if("description".equals(e.getName())){
//								String text = e.getText();
//								rss.setImgUrl(HtmlUtil.getImgSrc(text));
//							}
//						}
//						rssList.add(rss);
//					}
//				}
//			}
//		}catch(Exception e){
//			throw new BusinessException("解析rss源出错");
//		}finally{
//			try {
//				if(in!=null){
//					in.close();
//				}
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		return rssList;
//	}
//}
