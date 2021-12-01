package idv.amazingsora.ash.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/rf")
public class RestFulTestController {
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public Object search(@PathVariable String id) {
		Object result = null;
		JSONObject jsonObject = new JSONObject();  
		jsonObject.put("id", id);
		jsonObject.put("name", "a");
		jsonObject.put("sp", "!#)$");
		if(!StringUtils.equals(id, "a")){
			System.out.println("獲取陣列");
			JSONArray arr = new JSONArray();  
			arr.add(jsonObject);
			JSONObject ob = new JSONObject();  
			ob.put("id", "f");
			ob.put("name", "f");
			ob.put("sp", "f");
			arr.add(ob);

			result = arr;

		}else {
			result = jsonObject;
		}			
		return result;

	}

}
