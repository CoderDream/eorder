package com.innovaee.eorder.web.action.admin.function;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.innovaee.eorder.module.entity.Function;
import com.innovaee.eorder.module.entity.RoleFunction;
import com.innovaee.eorder.module.service.FunctionService;
import com.innovaee.eorder.module.service.RoleFunctionService;
import com.innovaee.eorder.module.utils.MenuUtil;
import com.innovaee.eorder.module.vo.FunctionVO;
import com.innovaee.eorder.module.vo.RoleLinkVo;
import com.innovaee.eorder.web.action.BaseAction;

public class FunctionAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(FunctionAction.class);

	private List<RoleLinkVo> menulist = new ArrayList<RoleLinkVo>();

	private String functionId;
	private String functionName;
	private String functionDesc;
	private String functionPath;
	private Integer functionParent;
	private String functionOrder;
	private String[] functionIds;
	private List<FunctionVO> functionvos;

	@Resource
	private FunctionService functionService;

	@Resource
	private RoleFunctionService roleFunctionService;

	private String contextPath;

	public void refreshData() {
		this.setMenulist(MenuUtil.getRoleLinkVOList());
		functionvos = functionService.findAllFunctionVOs();
	}

	public String login() {
		logger.debug("enter login() method");

		// 更新页面数据
		refreshData();
		return SUCCESS;
	}

	public String doFunction() {
		logger.debug("enter doFunction() method");

		// 更新页面数据
		refreshData();
		return SUCCESS;
	}

	public String doLoad() {
		if (null != functionId && !"".equals(functionId.trim())) {
			Function function = functionService.loadFunction(Integer
					.parseInt(functionId));

			functionName = function.getFunctionName();
			functionDesc = function.getFunctionDesc();
			functionPath = function.getFunctionPath();
			functionParent = function.getFunctionParent();
			functionOrder = function.getFunctionOrder();
		}

		// 更新页面数据
		refreshData();
		return SUCCESS;
	}

	public String doList() {
		// 更新页面数据
		refreshData();
		return SUCCESS;
	}

	public String doStore() {
		Function function = new Function();
		if (null != functionName && !"".equals(functionName.trim())) {
			function.setFunctionName(functionName);
		}

		if (null != functionDesc && !"".equals(functionDesc.trim())) {
			function.setFunctionDesc(functionDesc);
		}

		if (null != functionPath && !"".equals(functionPath.trim())) {
			function.setFunctionPath(functionPath);
		}

		if (0 != functionParent) {
			function.setFunctionParent(functionParent);
		}

		if (null != functionOrder && !"".equals(functionOrder.trim())) {
			function.setFunctionOrder(functionOrder);
		}
		function.setFunctionStatus(true);
		functionService.saveFunction(function);

		// 更新页面数据
		refreshData();
		return SUCCESS;
	}

	public String doUpdate() {
		Function function = null;
		if (null != functionId) {
			function = functionService.loadFunction(Integer
					.parseInt(functionId));
		}

		if (null != functionName && !"".equals(functionName.trim())) {
			function.setFunctionName(functionName);
		}

		if (null != functionDesc && !"".equals(functionDesc.trim())) {
			function.setFunctionDesc(functionDesc);
		}

		if (null != functionPath && !"".equals(functionPath.trim())) {
			function.setFunctionPath(functionPath);
		}

		if (0 != functionParent) {
			function.setFunctionParent(functionParent);
		}

		if (null != functionOrder && !"".equals(functionOrder.trim())) {
			function.setFunctionOrder(functionOrder);
		}

		functionService.updateFunction(function);

		// 更新页面数据
		refreshData();
		return SUCCESS;
	}

	public String doRemove() {
		if (null != functionId) {
			// 先判断角色功能关联关系，如果此功能已授权给某个角色，则不能删除
			List<RoleFunction> myRoleFunctions = roleFunctionService
					.findRoleFunctionsByFunctionId(Integer.parseInt(functionId));
			if (null == myRoleFunctions || 0 == myRoleFunctions.size()) {
				functionService.removeFunction(Integer.parseInt(functionId));
			}
		} else {
			functionService.removeFunctions(functionIds);
		}

		// 更新页面数据
		refreshData();
		return SUCCESS;
	}

	public String doUserInfo() {
		logger.debug("enter doUserInfo() method");

		// 更新页面数据
		refreshData();
		return SUCCESS;
	}

	public String doRight() {
		logger.debug("enter doRight() method");
		return SUCCESS;
	}

	public String doBottom() {
		logger.debug("enter doBottom() method");
		return SUCCESS;
	}

	public List<RoleLinkVo> getMenulist() {
		return menulist;
	}

	public void setMenulist(List<RoleLinkVo> menulist) {
		this.menulist = menulist;
	}

	public RoleFunctionService getRoleFunctionService() {
		return roleFunctionService;
	}

	public void setRoleFunctionService(RoleFunctionService roleFunctionService) {
		this.roleFunctionService = roleFunctionService;
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public List<FunctionVO> getFunctionvos() {
		return functionvos;
	}

	public void setFunctionvos(List<FunctionVO> functionvos) {
		this.functionvos = functionvos;
	}

	public FunctionService getFunctionService() {
		return functionService;
	}

	public void setFunctionService(FunctionService functionService) {
		this.functionService = functionService;
	}

	public String getFunctionId() {
		return functionId;
	}

	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}

	public String[] getFunctionIds() {
		return functionIds;
	}

	public void setFunctionIds(String[] functionIds) {
		this.functionIds = functionIds;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getFunctionDesc() {
		return functionDesc;
	}

	public void setFunctionDesc(String functionDesc) {
		this.functionDesc = functionDesc;
	}

	public String getFunctionPath() {
		return functionPath;
	}

	public void setFunctionPath(String functionPath) {
		this.functionPath = functionPath;
	}

	public Integer getFunctionParent() {
		return functionParent;
	}

	public void setFunctionParent(Integer functionParent) {
		this.functionParent = functionParent;
	}

	public String getFunctionOrder() {
		return functionOrder;
	}

	public void setFunctionOrder(String functionOrder) {
		this.functionOrder = functionOrder;
	}

}