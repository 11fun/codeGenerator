package ${package.Controller};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.common.util.BaseUtil;
import com.common.util.QueryRequest;
import com.common.util.Response;

import com.system.entity.${table.name?cap_first}Do;
import com.system.service.${table.name?cap_first}Service;


<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("/${table.name}")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>
	@Autowired
	${table.name?cap_first}Service ${table.name}Service;
	
	@GetMapping("/select")
	public Response  select(QueryRequest qr,${table.name?cap_first}Do ${table.name}) {
		Page<${table.name?cap_first}Do> page = new Page<>(qr.getPage(), qr.getLimit());
		QueryWrapper<${table.name?cap_first}Do> queryWrapper = BaseUtil.reflect(${table.name});
		Page<${table.name?cap_first}Do> pageData = ${table.name}Service.page(page, queryWrapper);
		return new Response().success().data(pageData);
	}
	
	@PostMapping("/insert")
	public Response insert(@Validated ${table.name?cap_first}Do ${table.name},BindingResult br) {
		
	}
	
	@PostMapping("/update")
	public Response update(@Validated ${table.name?cap_first}Do ${table.name},BindingResult br) {
		
	}
	
	@PostMapping("/delete")
	public Response delete(String id) {
		
	}
	

}
</#if>
