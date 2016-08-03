package com.aaa.test;


import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.aaa.entity.Grade;
import com.aaa.entity.Student;
import com.aaa.util.HibernateUtil;

/*
 * 单向一对多关系（班级-->学生）
 * 建立关联关系后，可以方便的从一个对象导航到另一个对象
 * 不过要注意关联的方向
 */
public class Test {
	public static void main(String[] args) {
		//add();
		//findStudentsByGrade();
		//update();
		delete();
	}
	
	//将学生添加到班级
	public static void add() {
		Grade grade = new Grade("Java一班", "Java软件开发一班");
		Student student1 = new Student("张三", "男");
		Student student2 = new Student("李小花", "女");
		
		//在学生表中添加对应的班级编号，在班级中添加学生编号，建立对应关系
		grade.getStudents().add(student1);
		grade.getStudents().add(student2);
		
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		session.save(grade);
		session.save(student1);
		session.save(student2);
		transaction.commit();
		HibernateUtil.closeSession(session);
	}
	
	//查询班级中包含的学生
	public static void findStudentsByGrade() {
		Session session = HibernateUtil.getSession();
		Grade grade = (Grade) session.get(Grade.class, 1);
		System.out.println(grade.getGname() + "," + grade.getGdesc());
		
		Set<Student> students = grade.getStudents();
		for(Student aStudent : students) {
			System.out.println(aStudent.getSname() + "," + aStudent.getSex());
		}
	}
	
	//修改学生信息
	public static void update() {
		Grade grade = new Grade("Java二班", "Java软件开发二班");
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		//读取学号为1的学生的信息
		Student aStudent = (Student) session.get(Student.class, 1);
		grade.getStudents().add(aStudent);
		session.save(grade);
		transaction.commit();
		HibernateUtil.closeSession(session);
	}
	
	//删除学生信息
	public static void delete() {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		Student aStudent = (Student) session.get(Student.class, 2);
		session.delete(aStudent);
		transaction.commit();
		HibernateUtil.closeSession(session);
	}
}
