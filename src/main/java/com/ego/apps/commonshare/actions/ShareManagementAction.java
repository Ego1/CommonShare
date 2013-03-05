package com.ego.apps.commonshare.actions;

import java.util.ArrayList;
import java.util.List;

import com.ego.apps.commonshare.actions.vo.AjaxResult;
import com.ego.apps.commonshare.actions.vo.CalculationUIVO;
import com.ego.apps.commonshare.actions.vo.CalculationVO;
import com.ego.apps.commonshare.actions.vo.PurchaseUIVO;
import com.ego.apps.commonshare.cache.SessionCache;
import com.ego.apps.commonshare.cache.SessionCacheManager;
import com.ego.apps.commonshare.dao.CalculationsDAO;
import com.ego.apps.commonshare.dao.entities.Calculation;
import com.ego.apps.commonshare.dao.entities.Purchase;
import com.ego.apps.commonshare.dao.entities.User;
import com.ego.apps.commonshare.exceptions.CSBusinessException;
import com.ego.apps.commonshare.util.CalculationsUtils;
import com.ego.apps.commonshare.util.JSONUtil;

public class ShareManagementAction extends BaseAction
	{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String description;		// Obtained when user wants a calculation.
	private AjaxResult ajaxResult;
	private List<CalculationUIVO> calculations;
	private CalculationUIVO calculation;
	private int id;
	private List<User> users;

	/* ************************************* Action Methods ************************************* */
	public String calculateShare()
		{
		// Find user's group.
		SessionCache cache = SessionCacheManager.getSessionCache(request);
		String groupName = cache.getUser().getGroup().getName();
		Calculation calculation = null;
		try
			{
			calculation = CalculationsUtils.performCalculation(groupName, description);
			}
		catch (CSBusinessException csBusinessException)
			{
			handleBusinessException(csBusinessException);
			}
		ajaxResult = new AjaxResult();
		ajaxResult.setResult(true);
		ajaxResult.setMessage("Calculation was done successfully. Please check the calculations page for more information.");
		ajaxResult.setData(JSONUtil.getCalculationJSON(calculation));
		// TODO: Need to show this calculation in user interface.
		return RESULT_SUCCESS;
		}

	public String showCalculations()
		{
		SessionCache cache = SessionCacheManager.getSessionCache(request);
		String groupName = cache.getUser().getGroup().getName();
		users = cache.getUser().getGroup().getUsers();
		CalculationsDAO calculationsDAO = new CalculationsDAO();
		List<Calculation> calculationObjects = calculationsDAO.getAllCalculations(groupName);
		;
		calculationsDAO.close();
		calculations = new ArrayList<CalculationUIVO>();
		for (Calculation calculation : calculationObjects)
			{
			CalculationUIVO calUivo = new CalculationUIVO(calculation);
			calculations.add(calUivo);
			}
		return RESULT_SUCCESS;
		}

	public String showACalculation()
		{
		SessionCache cache = SessionCacheManager.getSessionCache(request);
		String groupName = cache.getUser().getGroup().getName();
		users = cache.getUser().getGroup().getUsers();

		// Get the calculation information.
		CalculationsDAO calculationsDAO = new CalculationsDAO();
		CalculationVO calculationVO = calculationsDAO.getCalculationPurchases(id, groupName);
		if(calculationVO == null)
			{
			// There is no calculation matching this id by user group.
			addError("Either the calculation that you have selected is wrong, or your user group does not have permissions on this calculation.");
			return RESULT_ERROR;
			}
		calculation = new CalculationUIVO(calculationVO.getCalculation());
		calculation.setPurchases(new ArrayList<PurchaseUIVO>());
		for (Purchase purchase : calculationVO.getPurchases())
			{
			PurchaseUIVO purchaseUIVO = new PurchaseUIVO(purchase);
			calculation.getPurchases().add(purchaseUIVO);
			}
		return RESULT_SUCCESS;
		}

	/* ************************************* Getters and Setters ******************************** */
	public String getDescription()
		{
		return description;
		}

	public void setDescription(String description)
		{
		this.description = description;
		}

	public AjaxResult getAjaxResult()
		{
		return ajaxResult;
		}

	public void setAjaxResult(AjaxResult ajaxResult)
		{
		this.ajaxResult = ajaxResult;
		}

	public List<CalculationUIVO> getCalculations()
		{
		return calculations;
		}

	public void setCalculations(List<CalculationUIVO> calculations)
		{
		this.calculations = calculations;
		}

	public List<User> getUsers()
		{
		return users;
		}

	public void setUsers(List<User> users)
		{
		this.users = users;
		}

	public int getId()
		{
		return id;
		}

	public void setId(int id)
		{
		this.id = id;
		}

	public CalculationUIVO getCalculation()
		{
		return calculation;
		}

	public void setCalculation(CalculationUIVO calculation)
		{
		this.calculation = calculation;
		}

	}
