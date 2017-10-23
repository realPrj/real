package icia.project.web;

import java.util.Locale;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import icia.project.bean.BoardBean;
import icia.project.bean.LearningRoomBean;
import icia.project.bean.MemberBean;
import icia.project.services.PageManagement;


@Controller
public class HomeController  {

	@Autowired
	private PageManagement pm;

	private ModelAndView mav;


	///////////////////////////////////////// 페이지  /////////////////////////////////////////	

	// 홈(처음 시작)
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, Model model) {

		mav = new ModelAndView();

		mav.setViewName("home");

		return mav;
	}

	// ajax 테스트
	@RequestMapping(value = "/ajaxTest", method = RequestMethod.GET)
	public String start() {

		return "ajaxTest";
	}


	// 신분 선택(로그인 페이지 이동)
	@RequestMapping(value = "/IdentificationCheck", method = RequestMethod.POST)
	public ModelAndView identity(@ModelAttribute MemberBean member) {

		mav = new ModelAndView();

		mav.addObject("identity", member.getIdentity());
		mav.setViewName("login");

		return mav;
	}

	// 회원가입페이지
	@RequestMapping(value = "/joinPage", method = RequestMethod.POST)
	public ModelAndView joinPage(@ModelAttribute MemberBean member) {

		mav = new ModelAndView();

		switch(Integer.parseInt(member.getIdentity())) {

		case 1:	// 선생님
			mav.addObject("identity", member.getIdentity());
			mav.setViewName("teacherjoin");
			break;

		case 2:	// 학생
			mav.addObject("identity", member.getIdentity());
			mav.setViewName("studentjoin");
			break;

		default :
			mav.addObject("message", "alert('신분을 선택해주세요')");
			mav.setViewName("home");	
			break;

		}

		return mav;
	}

	// 아이디찾기 페이지
	@RequestMapping(value = "/idFindPage", method = RequestMethod.POST)
	public ModelAndView idFindPage(@ModelAttribute MemberBean member) {

		mav = new ModelAndView();

		mav.addObject("identity", member.getIdentity());
		mav.setViewName("findId");

		return mav;
	}
	
	// 선생님 나의 정보 페이지
	@RequestMapping(value = "/teacherInfoPage", method = RequestMethod.POST)
	public ModelAndView teacherInfoPage() {

		mav = pm.entrance(5, null);
		
		return mav;
	}
	
	// 선생님 나의 정보 수정 페이지
	@RequestMapping(value = "/teacherInfoUpdatePage", method = RequestMethod.POST)
	public ModelAndView teacherInfoUpdatePage(@ModelAttribute MemberBean member) {
		
		mav = new ModelAndView();
		
		mav.addObject("name", member.getName());
		mav.addObject("email", member.getEmail());
		mav.addObject("phone", member.getPhone());
		mav.setViewName("teacherInfoUpdate");
		
		return mav;
	}

	// 선생님 학습메뉴
	@RequestMapping(value = "/tcmenu", method = RequestMethod.POST)
	public ModelAndView tcMenu(@ModelAttribute BoardBean board) {

<<<<<<< HEAD
		mav = new ModelAndView();
		//System.out.println(board.getCaCode());
		int code = Integer.parseInt(board.getCaCode());
		switch(code) {
		case 1 : 
			
			break;
		case 2 : 
			
			break;
		case 3 : 
			
			break;
		case 4 : 
			pm.entrance(5, board);
			break;
		case 5 : 
			
			break;
		}
		mav = pm.entrance(Integer.parseInt(board.getCaCode()), board);
		
		//mav.setViewName("learningNotice");
=======
		switch(Integer.parseInt(board.getCaCode())){
		
		
		}
>>>>>>> 3989a7431f055c5ad66cfec5d0757caedc7b0853

		return mav;
	}


	// 학생 학습메뉴
	@RequestMapping(value = "/stmenu", method = RequestMethod.POST)
	public ModelAndView stMenu(@ModelAttribute MemberBean member) {

		mav = new ModelAndView();

		pm.entrance(Integer.parseInt(member.getCaCode()), member);

		return mav;
	}


	// 선생님 학습개설 페이지
	@RequestMapping(value = "/learningOpenPage", method = RequestMethod.POST)
	public String learningOpenPage(@ModelAttribute MemberBean member) {

		return "teacherLearningInsert";
	}

	// 학생 학습참여 페이지
	@RequestMapping(value = "/learningJoinPage", method = RequestMethod.POST)
	public String learningJoinPage(@ModelAttribute MemberBean member) {

		return "learningJoin";
	}
	
	// 선생님 학습방 메인 페이지
	@RequestMapping(value = "/teacherLearningMainPage", method = RequestMethod.POST)
	public ModelAndView teacherLearningMainPage(@ModelAttribute LearningRoomBean room) {

		mav = pm.entrance(3, room);
		
		return mav;
	}
	

	
	
	
	
	


}
