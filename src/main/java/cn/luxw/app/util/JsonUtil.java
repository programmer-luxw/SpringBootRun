package cn.luxw.app.util;


/**
 * http://zhangyou1010.iteye.com/blog/1049259
 * @author Administrator
 *
 */
public final class JsonUtil {
//	private static final SerializerFeature[] features = { 
//		SerializerFeature.DisableCircularReferenceDetect,
//		SerializerFeature.WriteMapNullValue,//
//		SerializerFeature.WriteNullListAsEmpty,//
//		SerializerFeature.WriteNullNumberAsZero,//
//		SerializerFeature.WriteNullBooleanAsFalse,//
//		SerializerFeature.WriteNullStringAsEmpty, //
//		SerializerFeature.WriteDateUseDateFormat
//	};
//	private static Logger logger = LoggerFactory.getLogger(JsonUtil.class); 
//	
//	public static final JsonUtil INSTANCE = buildNormalBinder();
//	private ObjectMapper mapper;
//
//	private JsonUtil(Inclusion inclusion) {
//		this.mapper = new ObjectMapper();
//
//		this.mapper.getSerializationConfig().withSerializationInclusion(inclusion); //设置输出包含的属性 
//		 //设置输入时忽略JSON字符串中存在而Java对象实际没有的属性 
//		this.mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//
//		this.mapper.configure(DeserializationConfig.Feature.FAIL_ON_NUMBERS_FOR_ENUMS, true);
//	}
//
//	private static JsonUtil buildNormalBinder() {
//		return new JsonUtil(Inclusion.ALWAYS);
//	}
//
//	/**
//	 * 把JSON串 转换成对象
//	 * @param jsonString
//	 * @param entityClass
//	 * @return
//	 */
//	public <T> T fromJson(String jsonString, Class<T> entityClass)  {
//		try {
//			if (!Strings.isNullOrEmpty(jsonString))
//				return this.mapper.readValue(jsonString, entityClass);
//			return null;
//		} catch (IOException e) {
//			throw new RuntimeException("JSON字符串转换错误", e);
//		}
//	}
//	
//	
//	
//	/**
//	 * JSON串  转换成泛型
//	 * @param jsonString
//	 * @param javaType
//	 * @return
//	 */
//	public <T> T fromJson(String jsonString, JavaType javaType)  {
//		try {
//			if (!Strings.isNullOrEmpty(jsonString))
//				return this.mapper.readValue(jsonString, javaType);
//			return null;
//		} catch (IOException e) {
//			throw new RuntimeException("JSON字符串转换错误", e);
//		}
//	}
//
//	/** 
//	 * Jackson处理一般的JavaBean和Json之间的转换只要使用ObjectMapper 
//	 * 对象的readValue和writeValueAsString两个方法就能实现。 
//	 * 但是如果要转换复杂类型Collection如 List<YourBean>，那么就需要先反序列化复杂类型 为泛型的Collection Type。
//	 * 或者用 TypeReference<T>
//	 */
//	public JavaType constructParametricType(Class<?> collectionClass, Class<?>... elementClasses) {
//		return this.mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
//	}
//
//	public <T> T update(T object, String jsonString)  {
//		try {
//			if (!Strings.isNullOrEmpty(jsonString))
//				return this.mapper.readerForUpdating(object).readValue(jsonString);
//			return object;
//		} catch (IOException e) {
//			throw new RuntimeException("JSON字符串更新对象错误", e);
//		}
//	}
//
//	/**
//	 * 把对象转换成JSON串
//	 * @param object
//	 * @return
//	 */
//	public String toJson(Object object)  {
//		try {
//			if (null == object)
//				return null;
//			return this.mapper.writeValueAsString(object);
//		} catch (IOException e) {
//			throw new RuntimeException("对象转换成JSON错误", e);
//		}
//	}
//
//	public String prettyJson(Object object)  {
//		JsonGenerator generator = null;
//		try {
//			StringWriter out = new StringWriter();
//			generator = this.mapper.getJsonFactory().createJsonGenerator(out);
//			generator.useDefaultPrettyPrinter();
//			this.mapper.writeValue(generator, object);
//			generator.flush();
//			return out.toString();
//		} catch (IOException e) {
//			throw new RuntimeException("对象转格式打印JSON错误", e);
//		} finally {
//			closeJsonGeneratorIfNecessary(generator);
//		}
//	}
//
//	public String toJsonp(String functionName, Object object) {
//		return toJson(new JSONPObject(functionName, object));
//	}
//
//	public ObjectMapper getMapper() {
//		return this.mapper;
//	}
//
//	public static void closeJsonGeneratorIfNecessary(JsonGenerator generator) {
//		if ((generator == null) || (generator.isClosed()))
//			return;
//		try {
//			generator.close();
//		} catch (IOException ex) {
//		}
//	}
}