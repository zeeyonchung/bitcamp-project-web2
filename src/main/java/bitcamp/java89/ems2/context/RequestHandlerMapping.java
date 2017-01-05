package bitcamp.java89.ems2.context;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;

import bitcamp.java89.ems2.annotation.RequestMapping;

public class RequestHandlerMapping {
  HashMap<String, RequestHandler> handlerMap = new HashMap<>();
  
  
  public RequestHandlerMapping(Collection<Object> objList) {
    for (Object obj : objList) {
      Method[] methods = obj.getClass().getMethods();
      for (Method m : methods) {
        RequestMapping anno = m.getAnnotation(RequestMapping.class);
        if (anno == null) {
          continue;
        }

        handlerMap.put(anno.value(), new RequestHandler(obj, m));
      }
    }
    
  }
  
  
  public RequestHandler getRequestHandler(String command) {
    return handlerMap.get(command);
  }
  
  
  public static class RequestHandler {
    public Object obj;
    public Method method;
    
    public  RequestHandler(Object obj, Method method) {
      this.obj = obj;
      this.method = method;
    }
  }
  
}
