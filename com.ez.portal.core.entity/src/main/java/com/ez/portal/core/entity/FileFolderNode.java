package com.ez.portal.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "FILE_FOLDER_NODE")
@XmlRootElement(name = "fileFolderNode")
public class FileFolderNode extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "FileFolderNodeIdGenerator")
	@GenericGenerator(name = "FileFolderNodeIdGenerator", strategy = "org.hibernate.id.TableHiLoGenerator")
	@Column(name = "FILE_FOLDER_NODE_ID")
	private Long fileFolderNodeId;

	@Column(name = "FILE_FOLDER_NODE_NAME")
	private String fileFolderNodeName;

	@Column(name = "FILE_FOLDER_NODE_TEXT")
	private String fileFolderNodeText;

	@Column(name = "FILE_FOLDER_NODE_TYPE")
	private Byte fileFolderNodeType;

	@ManyToOne
	@JoinColumn(name = "FILE_FOLDER_NODE_PARENT")
	private FileFolderNode fileFolderNodeParent;

	public Long getFileFolderNodeId() {
		return fileFolderNodeId;
	}

	public void setFileFolderNodeId(Long fileFolderNodeId) {
		this.fileFolderNodeId = fileFolderNodeId;
	}

	public String getFileFolderNodeName() {
		return fileFolderNodeName;
	}

	public void setFileFolderNodeName(String fileFolderNodeName) {
		this.fileFolderNodeName = fileFolderNodeName;
	}

	public String getFileFolderNodeText() {
		return fileFolderNodeText;
	}

	public void setFileFolderNodeText(String fileFolderNodeText) {
		this.fileFolderNodeText = fileFolderNodeText;
	}

	public Byte getFileFolderNodeType() {
		return fileFolderNodeType;
	}

	public void setFileFolderNodeType(Byte fileFolderNodeType) {
		this.fileFolderNodeType = fileFolderNodeType;
	}

	public FileFolderNode getFileFolderNodeParent() {
		return fileFolderNodeParent;
	}

	public void setFileFolderNodeParent(FileFolderNode fileFolderNodeParent) {
		this.fileFolderNodeParent = fileFolderNodeParent;
	}

}
