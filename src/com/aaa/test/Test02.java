package com.aaa.test;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.aaa.entity.Grade;
import com.aaa.entity.Student;
import com.aaa.util.HibernateUtil;

/*
 * 测试单向多对一的关联关系（学生-->班级）
 * 既配置了一对多，也配置了多对一，也称双向多对一
 * 既可以方便的的通过学生查找对应的班级，也可以通过班级查找相对应的学生
 */
public class Test02 {
	public static void main(String[] args) {
		//save();
		findGradeByStudentAndInverse();
	}
	
	//保存学生信息
	public static void save() {
		Grade grade = new Grade("Java一班", "Java开发软件一班");
		Student student1 = new Student("张三", "男");
		Student student2 = new Student("李小花", "女");
		//设置关联关系
		grade.getStudents().add(student1);
		grade.getStudents().add(student2);
		student1.setGrade(grade);
		student2.setGrade(grade);
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		//grade.hbm.xml中添加级联的配置
		session.save(grade);
		//session.save(student1);
		//session.save(student2);
		transaction.commit();
		HibernateUtil.closeSession(session);
	}
	
	//由学生查找对应班级，由班级查找相对应的学生
	public static void findGradeByStudentAndInverse() {
		Session session = HibernateUtil.getSession();
		//通过学生查找班级
		Student aStudent = (Student) session.get(Student.class, 3);
		Grade grade = aStudent.getGrade();
		System.out.println(aStudent.getSname() + ": " + grade.getGname());
		
		//通过班级查找学生
		Grade grade2 = (Grade) session.get(Grade.class, 3);
		Set<Student> gradeStudent = grade2.getStudents();
		for(Student stu : gradeStudent) {
			System.out.println(grade2.getGname()+ ": " + stu.getSname());
		}
		
		HibernateUtil.closeSession(session);
	}
}
