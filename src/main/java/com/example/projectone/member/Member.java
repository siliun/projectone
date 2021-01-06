package com.example.projectone.member;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//@Getter
//@Setter
//@ToString
//@RequiredArgsConstructor
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SequenceGenerator(
		name = "MEMBER_SEQ_GENERATOR", 
		sequenceName = "MEMBER_SEQ", // 매핑할 데이터베이스 시퀀스 이름 
		initialValue = 1,
		allocationSize = 1)
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
	long idx;
	
	@Column
	String userId;
	
	@Column
	String userName;
	
	@Column
	String passwd;
	
}
