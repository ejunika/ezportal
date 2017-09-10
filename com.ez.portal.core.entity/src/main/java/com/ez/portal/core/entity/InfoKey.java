package com.ez.portal.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author azaz.akhtar
 *
 */
@Entity
@Table(name = "INFO_KEY")
@XmlRootElement(name = "infoKey")
public class InfoKey extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Id
	@GeneratedValue(generator = "InfoKeyIdGenerator")
	@GenericGenerator(name = "InfoKeyIdGenerator", strategy = "org.hibernate.id.TableHiLoGenerator")
	@Column(name = "INFO_KEY_ID")
	private Long infoKeyId;

	/**
	 * 
	 */
	@Column(name = "INFO_KEY_TYPE")
	private Byte infoKeyType;

	/**
	 * 
	 */
	@Column(name = "INFO_KEY_ORDER")
	private Integer infoKeyOrder;

	/**
	 * 
	 */
	@Column(name = "INFO_KEY_NAME")
	private String infoKeyName;

	/**
	 * 
	 */
	@Column(name = "INFO_KEY_DISPLAY_NAME")
	private String infoKeyDisplayName;

	public InfoKey() {
		super();
	}

	public InfoKey(Byte infoKeyType, Integer infoKeyOrder, String infoKeyName, String infoKeyDisplayName) {
		super();
		this.infoKeyType = infoKeyType;
		this.infoKeyOrder = infoKeyOrder;
		this.infoKeyName = infoKeyName;
		this.infoKeyDisplayName = infoKeyDisplayName;
	}

	public InfoKey(Byte infoKeyType, Integer infoKeyOrder, String infoKeyName, String infoKeyDisplayName,
			Byte entryStatus, String shardKey) {
		super(entryStatus, shardKey);
		this.infoKeyType = infoKeyType;
		this.infoKeyOrder = infoKeyOrder;
		this.infoKeyName = infoKeyName;
		this.infoKeyDisplayName = infoKeyDisplayName;
	}

	public Long getInfoKeyId() {
		return infoKeyId;
	}

	public void setInfoKeyId(Long infoKeyId) {
		this.infoKeyId = infoKeyId;
	}

	public Byte getInfoKeyType() {
		return infoKeyType;
	}

	public void setInfoKeyType(Byte infoKeyType) {
		this.infoKeyType = infoKeyType;
	}

	public Integer getInfoKeyOrder() {
		return infoKeyOrder;
	}

	public void setInfoKeyOrder(Integer infoKeyOrder) {
		this.infoKeyOrder = infoKeyOrder;
	}

	public String getInfoKeyName() {
		return infoKeyName;
	}

	public void setInfoKeyName(String infoKeyName) {
		this.infoKeyName = infoKeyName;
	}

	public String getInfoKeyDisplayName() {
		return infoKeyDisplayName;
	}

	public void setInfoKeyDisplayName(String infoKeyDisplayName) {
		this.infoKeyDisplayName = infoKeyDisplayName;
	}

}
