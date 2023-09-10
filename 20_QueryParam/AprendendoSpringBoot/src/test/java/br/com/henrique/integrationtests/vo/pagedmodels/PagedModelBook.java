package br.com.henrique.integrationtests.vo.pagedmodels;

import java.util.List;
import java.util.Objects;

import br.com.henrique.integrationtests.vo.BookVO;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PagedModelBook {

	@XmlElement(name="content")
	private List<BookVO> content;

	public PagedModelBook() {
	}

	public List<BookVO> getContent() {
		return content;
	}

	public void setContent(List<BookVO> content) {
		this.content = content;
	}

	@Override
	public int hashCode() {
		return Objects.hash(content);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PagedModelBook other = (PagedModelBook) obj;
		return Objects.equals(content, other.content);
	}
}
