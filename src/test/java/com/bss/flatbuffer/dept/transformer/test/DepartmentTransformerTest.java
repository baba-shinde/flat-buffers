package com.bss.flatbuffer.dept.transformer.test;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bss.flatbuffer.dept.business.dto.Book;
import com.bss.flatbuffer.dept.business.dto.Department;
import com.bss.flatbuffer.dept.business.dto.Genre;
import com.bss.flatbuffer.dept.business.dto.Student;
import com.bss.flatbuffer.dept.business.dto.Subject;
import com.bss.flatbuffer.dept.transformer.DepartmentTransformer;

import junit.framework.Assert;

public class DepartmentTransformerTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentTransformerTest.class);
	private DepartmentTransformer departmentTransformer;

	@Before
	public void setUp() {
		departmentTransformer = new DepartmentTransformer();
	}

	@Test
	public void testTransformation() {
		String departmentName = "Department Of Mathematics";
		int departmentId = 1;
		String tag = "No 1 Dept";
		Genre bookGenre = Genre.Educational;
		Subject favSubject = Subject.Maths;
		Department testData = createTestData(departmentId, departmentName, tag, bookGenre, favSubject);
		LOGGER.info("Original Object: {}", testData);
		byte[] serializedData = departmentTransformer.serialize(testData);
		
		Department transformedObj = departmentTransformer.deserialize(serializedData);
		LOGGER.info("Transformed Object: {}", transformedObj);

		Assert.assertSame("Should be same", departmentId, transformedObj.getDepartmentId());
		Assert.assertEquals("Should be same", departmentName, transformedObj.getDepartmentName());
		Assert.assertEquals("Should be same", tag, transformedObj.getDepartmentTag());
		Assert.assertEquals("Should be same", testData.getBooks().size(), transformedObj.getBooks().size());
		Assert.assertEquals("Should be same", testData.getStudents().size(), transformedObj.getStudents().size());
	}

	private Department createTestData(
			final int departmentId, 
			final String departmentName, 
			final String tag,
			final Genre bookGenre,
			final Subject favSubject) {
		return new Department() {{
			setDepartmentId(departmentId);
			setDepartmentName(departmentName);
			setDepartmentTag(tag);
			setBooks(new ArrayList<Book>() {{
				add(new Book() {{
					setBookId("Battle of Panipat");
					setAuthorId("Unknown");
					setAuthorDesc("Author Not known");
					setGenre(bookGenre);
				}});
				add(new Book() {{
					setBookId("Shiva Triology");
					setAuthorId("Patel");
					setAuthorDesc("Popular Author");
					setGenre(bookGenre);
				}});
			}});
			setStudents(new ArrayList<Student>() {{
				add(new Student() {{
					setStudentId(111);
					setStudentName("Vihaan Shinde");
					setAddress("Pune");
					setFavSubject(favSubject);
				}});
				add(new Student() {{
					setStudentId(112);
					setStudentName("Tanush Shinde");
					setAddress("Chinchwad");
					setFavSubject(favSubject);
				}});
				add(new Student() {{
					setStudentId(113);
					setStudentName("BSS");
					setAddress("Pune");
					setFavSubject(favSubject);
				}});
			}});
		}};
	}
}