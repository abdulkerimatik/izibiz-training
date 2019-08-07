package com.izibiz.training.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;


import com.izibiz.training.bean.base.GenericBean;
import com.izibiz.training.entity.dto.DataRepo;
import com.izibiz.training.entity.dto.ReconciDTO;
import com.izibiz.training.lazy.model.InvoiceDtoLazyModel;
import com.izibiz.training.lazy.model.ReconciliationDtoLazyModel;
import com.izibiz.training.entity.Reconciliation;
import com.izibiz.training.entity.User;




@ViewScoped
@ManagedBean(name="reconciliationBean")
public class ReconciliationBean extends GenericBean<ReconciliationBean> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private ReconciDTO reconciDto;
	//private Reconciliation reconciD;
	//private List<ReconciDTO> reconciDTOs;
	//private List<ReconciDTO> listDTOs;
	private ReconciDTO selectedReconciDto;
	private Reconciliation selectedReconciliation;
	private List<Reconciliation> reconciList;
	private Reconciliation reconciliation;
	private ReconciliationDtoLazyModel reconciliationDtoLazyModel;
	private List<Reconciliation> checkReconciliaton;
	private String valueText;
	public boolean opaciTy=false;
	
	

	public void openViewReconciPage() {
		
		
		setReconciList(new ArrayList<Reconciliation>());
		reconciList.addAll(getReconciliationService().getAlls());
		
		/*setReconciDTOs(new ArrayList<ReconciDTO>());
		reconciDTOs.addAll(DataRepo.reconci);*/
		//selectedReconciliation=null;
		//valueText=new String();
		clearReconci();
	}
	public void openViewReconciTableList() {
		Map<String, Object> filter=new HashMap<String, Object>();
		filter.put("accountID", 2);
		filter.put("direction", "OUT");
		reconciliationDtoLazyModel=new ReconciliationDtoLazyModel(getReconciliationService());
		reconciliationDtoLazyModel.setFiltermap(filter);
	}
	public void openViewReconciPageInsert() {
		User user = getUserService().findByUsernanme("ALİ");
		System.out.println(user!=null ? user.toString() :" could not found user");
		reconciList = new ArrayList<Reconciliation>();
		reconciList.addAll(getReconciliationService().getAlls());
	}
	public void clearReconci() {
		reconciliation= new Reconciliation();
		reconciliation.setUuid(UUID.randomUUID().toString());
		selectedReconciliation = null;
	}
	public void tableOpacity() {
		if(StringUtils.isEmpty(valueText)) {
			opaciTy=false;
			//System.out.print(valueText);
		}
		else {
		opaciTy=true;
		//System.out.print(valueText);
		}
		addInfoMessage("İşlem Başarı ile Gerçekleştirildi");
		
	}
	/*public Boolean panelop() {
		if(StringUtils.isEmpty(valueText)) {
			return false;
		}else {
			return true;
		}
		//style="#{reconciliationBean.panelop()?'':'opacity: 0.2;'}"
	}*/

	public void saveReconci() {
		/*validationFailed(reconciDto);
		if(FacesContext.getCurrentInstance().isValidationFailed()) {
			return;
		}	*/	
		if(!validationFailed(reconciliation))	
			return;
		selectedReconciliation=null;
		reconciliation.setCustomerPartyId(1);
		reconciliation.setAcountId(2);
		reconciliation.setStatus("SUCCED");
			if(reconciliation.getStatus().equals("SUCCED")) {
				reconciliation.setDIRECTION("DRAFT");
			}
		getReconciliationService().saveOrUpdate(reconciliation);
		openViewReconciPage();
		addInfoMessage("Oluşturma işlemi başarılı");
	}
	public void editReconci() {
		if(!validationFailed(selectedReconciliation)) {
			return;}
		getReconciliationService().saveOrUpdate(selectedReconciliation);
		openViewReconciPage();
		addInfoMessage("Düzenleme işleme başarılı");
		
		
	}
	public void deleteReconci() {
		if (selectedReconciliation != null) {
			
			getReconciliationService().delete(selectedReconciliation);
			
			openViewReconciPage();
			addInfoMessage("Silme işlemi başarılı şekilde tamamlanmıştır.");
			selectedReconciliation = null;
		}
	}
	public void addToQue() {
		changeStatus("ADDEDQUE");
		if(selectedReconciliation.getStatus().equals("ADDEDQUE")) {
			selectedReconciliation.setDIRECTION("OUT");
		}
		getReconciliationService().saveOrUpdate(selectedReconciliation);
		openViewReconciPage();
		addInfoMessage("Başarı ile kuyruğa eklendi");
	}
	public void sentReconci() {
		//archiveDto.setStatus("LOAD");
		/*setListDTOs(new ArrayList<ReconciDTO>());
		for (ReconciDTO reconciDTO : reconciDTOs) {
			if (reconciDTO.getUuId().equals(selectedReconciDto.getUuId())) {
				DataRepo.reconci.remove(reconciDTO);
				DataRepo.reconcisent.add(selectedReconciDto);
				//archiveDTOs.remove(o)	
			}
		}
		selectedReconciliation.setStatus("LOAD");*/
		changeStatus("LOAD");
		if(selectedReconciliation.getStatus().equals("LOAD")) {
			selectedReconciliation.setDIRECTION("OUT");
		}
		getReconciliationService().saveOrUpdate(selectedReconciliation);
		openViewReconciPage();
		addInfoMessage("Fatura Başarılıyla Gönderildi");
	}
	private boolean validationFailed(Reconciliation rciDto) {
		if (rciDto == null) {
			addErrorMessage("InvoiceDTO boş olamaz");
			FacesContext.getCurrentInstance().validationFailed();
			return false;
		}else if(rciDto.getLoadingDate()==null || StringUtils.isBlank(rciDto.getLoadingDate().toString())){
			addErrorMessage("tarih hatası");
			FacesContext.getCurrentInstance().validationFailed();
			return false;
		}else if(StringUtils.isBlank(rciDto.getSender())){
			addErrorMessage("satıcı hatası");
			FacesContext.getCurrentInstance().validationFailed();
			return false;
		}else if(rciDto.getAmount()==null || StringUtils.isBlank(rciDto.getAmount().toString())){
			addErrorMessage("Miktar hatası");
			FacesContext.getCurrentInstance().validationFailed();
			return false;
		}
		return true;
	}
	public void changeStatus(String value) {
		if(selectedReconciliation!=null) {
			selectedReconciliation.setStatus(value);
		}
	}
	public String statusColor(String status) {
		if ("LOAD".equals(status)) {
			return "yellow";
		}else if("NEW".equals(status)){
			return "red";
		}else if("SUCCED".equals(status)){
			return "green";
		}else if("ADDEDQUE".equals(status)) {
			return "#fab5b5";
		}
		return "";
	}
	public String statusDesc(String status) {
		if("LOAD".equals(status)) {
			return "Yüklendi";
		}else if("NEW".equals(status)) {
			return "Kuyruga eklendi";
		}else if("SUCCED".equals(status)) {
			return "Başarılı";
		}else if("ADDEDQUE".equals(status)) {
			return "Kuyrağa eklendi";
		}
		return "";
	}
	/*public ReconciDTO getReconciDto() {
		return reconciDto;
	}
	public void setReconciDto(ReconciDTO reconciDto) {
		this.reconciDto = reconciDto;
	}
	public List<ReconciDTO> getReconciDTOs() {
		return reconciDTOs;
	}
	public void setReconciDTOs(List<ReconciDTO> reconciDTOs) {
		this.reconciDTOs = reconciDTOs;
	}
	public List<ReconciDTO> getListDTOs() {
		return listDTOs;
	}
	public void setListDTOs(List<ReconciDTO> listDTOs) {
		this.listDTOs = listDTOs;
	}
	*/
	public ReconciDTO getSelectedReconciDto() {
		return selectedReconciDto;
	}
	public void setSelectedReconciDto(ReconciDTO selectedReconciDto) {
		this.selectedReconciDto = selectedReconciDto;
	}
	public List<Reconciliation> getReconciList() {
		return reconciList;
	}
	public void setReconciList(List<Reconciliation> reconciList) {
		this.reconciList = reconciList;
	}
	public Reconciliation getReconciliation() {
		return reconciliation;
	}
	public void setReconciliation(Reconciliation reconciliation) {
		this.reconciliation = reconciliation;
	}
	public Reconciliation getSelectedReconciliation() {
		return selectedReconciliation;
	}
	public void setSelectedReconciliation(Reconciliation selectedReconciliation) {
		this.selectedReconciliation = selectedReconciliation;
	}
	public String getValueText() {
		return valueText;
	}
	public void setValueText(String valueText) {
		this.valueText = valueText;
	}
	public boolean isOpaciTy() {
		return opaciTy;
	}
	public void setOpaciTy(boolean opaciTy) {
		this.opaciTy = opaciTy;
	}
	public ReconciliationDtoLazyModel getReconciliationDtoLazyModel() {
		return reconciliationDtoLazyModel;
	}
	public void setReconciliationDtoLazyModel(ReconciliationDtoLazyModel reconciliationDtoLazyModel) {
		this.reconciliationDtoLazyModel = reconciliationDtoLazyModel;
	}
	public List<Reconciliation> getCheckReconciliaton() {
		return checkReconciliaton;
	}
	public void setCheckReconciliaton(List<Reconciliation> checkReconciliaton) {
		this.checkReconciliaton = checkReconciliaton;
	}
	
	
}
