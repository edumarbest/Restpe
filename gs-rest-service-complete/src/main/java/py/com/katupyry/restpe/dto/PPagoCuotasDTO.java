package py.com.katupyry.restpe.dto;

import java.math.BigDecimal;

public class PPagoCuotasDTO {
	/*ppago_cuotas 
    Documento		Varchar(20)	
    nombre_cliente	Varchar(250)
    id_fraccion		Number(6)
    id_manzana		Number(6)
    id_lote		Number(6)
    numero_cuota		Number(6)
    monto_cuota		Number(15)
    meses_mora		Number(3)
    monto_mora		Number(15)
    codigo_retorno	Number
    desc_retorno		Varchar(60)
    codigo_trans 	Varchar(20)*/
	
    private String documento;
	private String nombreCliente;
    private Integer idFraccion;
    private Integer idManzana;
    private Integer idLote;
    private Integer numeroCuota;
    private BigDecimal montoCuota;
    private Integer mesesMora;
    private BigDecimal montoMora;
    private Integer codigoRetorno;
    private String descRetorno;
    private Integer codigoTrans;

    public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public Integer getIdFraccion() {
		return idFraccion;
	}
	public void setIdFraccion(Integer idFraccion) {
		this.idFraccion = idFraccion;
	}
	public Integer getIdManzana() {
		return idManzana;
	}
	public void setIdManzana(Integer idManzana) {
		this.idManzana = idManzana;
	}
	public Integer getIdLote() {
		return idLote;
	}
	public void setIdLote(Integer idLote) {
		this.idLote = idLote;
	}
	public Integer getNumeroCuota() {
		return numeroCuota;
	}
	public void setNumeroCuota(Integer numeroCuota) {
		this.numeroCuota = numeroCuota;
	}
	public BigDecimal getMontoCuota() {
		return montoCuota;
	}
	public void setMontoCuota(BigDecimal montoCuota) {
		this.montoCuota = montoCuota;
	}
	public Integer getMesesMora() {
		return mesesMora;
	}
	public void setMesesMora(Integer mesesMora) {
		this.mesesMora = mesesMora;
	}
	public BigDecimal getMontoMora() {
		return montoMora;
	}
	public void setMontoMora(BigDecimal montoMora) {
		this.montoMora = montoMora;
	}
	public Integer getCodigoRetorno() {
		return codigoRetorno;
	}
	public void setCodigoRetorno(Integer codigoRetorno) {
		this.codigoRetorno = codigoRetorno;
	}
	public String getDescRetorno() {
		return descRetorno;
	}
	public void setDescRetorno(String descRetorno) {
		this.descRetorno = descRetorno;
	}
	public Integer getCodigoTrans() {
		return codigoTrans;
	}
	public void setCodigoTrans(Integer codigoTrans) {
		this.codigoTrans = codigoTrans;
	}
    
    
}
