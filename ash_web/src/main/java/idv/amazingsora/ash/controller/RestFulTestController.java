package idv.amazingsora.ash.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import idv.amazingsora.ash.util.PropertiesUntil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("/rf")
public class RestFulTestController {
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public String search(@PathVariable String id) {
		String a = PropertiesUntil.getKey("flow.highDeptUserApplyOff");
		System.out.println("A ===>" + a);

		JSONArray arr = new JSONArray();

		Object result = null;
		if (StringUtils.isNoneBlank(id)) {
			if (id.toUpperCase().equals("LBU")) {
				JSONObject ob = new JSONObject();
				ob.put("id", "LBU");
				ob.put("name", "LBU");
				ob.put("sp", "OVER");
				arr.add(ob);
			} else if (id.equals("keyword")) {
				return "";

			}

			else {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id", id);
				jsonObject.put("name", "default");
				jsonObject.put("sp", "!#)$");
				arr.add(jsonObject);
			}
		}
		result = arr;

		return result.toString();

	}

}
