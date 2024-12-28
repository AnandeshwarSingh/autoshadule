package org.anand.clientapp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.anand.model.CandidateModule;
import org.anand.model.HRModel;
import org.anand.model.candidateskills;
import org.anand.service.*;

import org.anand.service.AdminService;
import org.anand.service.AdminServiceImpl;

public class AutomaticInterviewSchedulingSystemClientApp { //main class
	public static void main(String args[]) {
		AdminService adminService = new AdminServiceImpl();
		HRService hrService = new HRServiceImpl();
		CandidateService candidateService = new CandidateServiceImpl();
		CandidateskillsService candService = new CandidateskillsServiceImpl();
		InterviewscheduleService intvService = new InterviewscheduleServiceImpl();

		Scanner sc = new Scanner(System.in);

		System.out.println("\n😊=================== Welcome to AISS ================😊");

		do {
			System.out.println("Enter apropreate Option");
			System.out.println("1:: Admin Login");
			System.out.println("2:: HR Login");
			System.out.println("3:: Candidate Login");
			System.out.println("4:: For exit");
			System.out.println("Slect your choice");
			System.out.println("===================");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Enter Admin email id");
				sc.nextLine();
				String aemail = sc.nextLine();
				System.out.println("Enter password");
				String apass = sc.nextLine();
				boolean b = adminService.loginAdmin(aemail, apass);
				if (b) {
					System.out.println("Login Successfull... 😊");

					do {
						System.out.println("Enter apropreate Option");
						System.out.println("1:: add HR");
						System.out.println("2:: Update HR");
						System.out.println("3:: Remove HR");
						System.out.println("4:: View HR's in database");
						System.out.println("5:: add Candidate");
						System.out.println("6:: Update Candidate");
						System.out.println("7:: Remove Candidate");
						System.out.println("8:: View Candidate's from database");
						System.out.println("9:: Search Candidate By name");
						System.out.println("10:: Search HR By name");
						System.out.println("11:: Schedule Interview of Candidate");
						System.out.println("12:: For Exit");
						System.out.println("13:: Scheduled Interview");
						System.out.println("Slect your choice");
						choice = sc.nextInt();
						System.out.println("===================");

						switch (choice) {
//-----------------------------------ADD HR--------------------------------------------------						
						case 1:
							sc.nextLine();
							System.out.println("Enter HR name");
							String hname = sc.nextLine();
							System.out.println("Enter HR email");
							String hemail = sc.nextLine();
							System.out.println("Enter HR Phone Number");
							String hphone = sc.nextLine();
							System.out.println("Enter a pasword for HR");
							String hpass = sc.nextLine();
							HRModel model = new HRModel(0, hname, hemail, hphone, hpass);
							boolean b2 = hrService.isAddHR(model);
							if (b2) {
								System.out.println("HR added!😎");
							} else {
								System.out.println("HR Not added🤦‍♂️");
							}
							break;
//--------------------------------Update HR--------------------------------------------------
						case 2:
							System.out.println("Enter HR ID to update :");
							int hrid = sc.nextInt();
							sc.nextLine();

							boolean contUpdate = true;

							while (contUpdate) {
								System.out.println("Which fileld do you want to update?");
								System.out.println("Option: name, email, phone, password");
								String field = sc.nextLine().toLowerCase();

								String newValue;

								switch (field) {
								case "name":
									System.out.println("Enter new name : ");
									newValue = sc.nextLine();
									break;

								case "email":
									System.out.println("Enter new email : ");
									newValue = sc.nextLine();
									break;

								case "phone":
									System.out.println("Enter new phone number : ");
									newValue = sc.nextLine();
									break;
								case "password":
									System.out.println("Enter new password : ");
									newValue = sc.nextLine();
									break;
								default:
									System.out.println("Invalid option. Please enter a valid field. 🤦‍♂️");
									continue;
								}

								boolean success = hrService.updateHRField(hrid, field, newValue);

								if (success) {
									System.out.println("HR " + field + " updated successfully. 😊");
								} else {
									System.out.println(
											"Failed to update HR details. Please check the HR ID or try again. 🤦‍♂️");
								}

								// if admin want to update more field
								System.out.println("Do you want to update any thing else? yes/no 🤞");
								String response = sc.nextLine().trim().toLowerCase();
								if (!response.equals("yes")) {
									contUpdate = false;
								}

							}

							break;
//---------------------------------Remove HR--------------------------------------------------														

						case 3:
							System.out.println("Enter HR id to remove");
							hrid = sc.nextInt();
							b = hrService.isRemiveHR(hrid);
							if (b) {
								System.out.println("HR is removed from list 😊");
							} else {
								System.out.println("Some problem is there... 🤦‍♂️");
							}

							break;
//---------------------------------View HR---------------------------------------------------							
						case 4:
							Optional<List<HRModel>> o = hrService.getAllHr();
							if (o.isPresent()) {
								List<HRModel> list = o.get();
								list.forEach((hr) -> System.out.println(hr.getHrid() + " " + hr.getHname() + " "
										+ hr.getHemail() + " " + hr.getHphone() + " " + hr.getHpassword()));
							} else {
								System.out.println("In HR Section there is no data 🤷‍♂️");
							}

							break;
//-----------------------------Add Candidate By Admin---------------------------------------
						case 5:

							System.out.println("Enter candidate Detail : ");
							System.out.println("Name:");
							String cname = sc.nextLine();
							System.out.println("Email:");
							String cemail = sc.nextLine();
							System.out.println("Phone :");
							String cphone = sc.nextLine();
							System.out.println("Address:");
							String caddress = sc.nextLine();
							System.out.println("Education:");
							String ceducation = sc.nextLine();
							System.out.println("Password");
							String cpassword = sc.nextLine();

							CandidateModule model1 = new CandidateModule(0, cname, cemail, cphone, caddress, ceducation,
									cpassword);
							boolean b3 = candidateService.isCandidateAdd(model1);

							if (b3) {
								System.out.println(" Candidate Added Successfull! 😊");
							} else {
								System.out.println("Failed to add candidate. 🤦‍♂️");
							}
							break;
//--------------------------------Update Candidate by Admin-----------------------------
						case 6:
							System.out.println("Enter Candidate ID to update :");
							int candidateid = sc.nextInt();
							sc.nextLine();

							boolean contUpdate2 = true;

							while (contUpdate2) {
								System.out.println("Which fileld do you want to update?");
								System.out.println("Option: name, email, phone, address, education, password");
								String field = sc.nextLine().toLowerCase();

								String newValue;

								switch (field) {
								case "name":
									System.out.println("Enter new name : ");
									newValue = sc.nextLine();
									break;

								case "email":
									System.out.println("Enter new email : ");
									newValue = sc.nextLine();
									break;

								case "phone":
									System.out.println("Enter new phone number : ");
									newValue = sc.nextLine();
									break;
								case "address":
									System.out.println("Enter new address");
									newValue = sc.nextLine();
									break;
								case "education":
									System.out.println("Enter new education");
									newValue = sc.nextLine();
									break;
								case "password":
									System.out.println("Enter new password : ");
									newValue = sc.nextLine();
									break;
								default:
									System.out.println("Invalid option. Please enter a valid field. 🤷‍♂️");
									continue;
								}

								boolean success = candidateService.updateCandidateField(candidateid, field, newValue);

								if (success) {
									System.out.println("Candidate " + field + " updated successfully. 😊");
								} else {
									System.out.println(
											"Failed to update HR details. Please check the HR ID or try again. 🤷‍♂️");
								}

								// if hr want to update more field
								System.out.println("Do you want to update any thing else? yes/no 🤞");
								String response = sc.nextLine().trim().toLowerCase();
								if (!response.equals("yes")) {
									contUpdate2 = false;
								}

							}

							break;
//------------------------------Remove Candidate By Admin-----------------------------------

						case 7:
							System.out.println("Enter candidate id to remove");
							int candidateId = sc.nextInt();
							sc.nextLine();

							b = candidateService.isRemiveCandidate(candidateId);
							if (b) {
								System.out.println("Candidate is removed from list 😊");
							} else {
								System.out.println("Some problem is there... 🤦‍♂️");

							}
							break;
//-----------------------------View Candidate from Data Base--------------------------------
						case 8:
							Optional<List<CandidateModule>> o1 = candidateService.getAllCandidate();
							if (o1.isPresent()) {
								List<CandidateModule> list = o1.get();
								list.forEach((can) -> System.out.println(can.getCandidate_id() + " " + can.getName()
										+ " " + can.getEmail() + " " + can.getPhone() + " " + can.getAddress() + " "
										+ can.getEducation() + " " + can.getPassword()));
							} else {
								System.out.println("In HR Section there is no data 🤷‍♂️");
							}
							break;
//--------------------------------Search Candidae by name------------------------------------							
						case 9:
							sc.nextLine();
							System.out.println("Enter the name to search for a candidate:");
							String cName = sc.nextLine().trim();
							System.out.println("===================");
							Optional<List<CandidateModule>> candidates = candidateService.searchCandidatesByName(cName);
							if (candidates.isPresent() && !candidates.get().isEmpty()) {
								List<CandidateModule> list = candidates.get();
								list.forEach((can) -> System.out.println(can.getCandidate_id() + " " + can.getName()
										+ " " + can.getEmail() + " " + can.getPhone() + " " + can.getAddress() + " "
										+ can.getEducation() + " " + can.getPassword()));
							} else {
								System.out.println("In Candidate Section there is no data like " + cName + " 🤷‍♂️");
							}
							System.out.println("===================");
							break;

						case 10:
							sc.nextLine();
							System.out.println("Enter the name to search for a HR:");
							String hrname = sc.nextLine().trim();
							System.out.println("===================");
							Optional<List<HRModel>> hrdata = hrService.searchHrByName(hrname);
							if (hrdata.isPresent() && !hrdata.get().isEmpty()) {
								List<HRModel> list = hrdata.get();
								list.forEach((hrd) -> System.out.println(hrd.getHrid() + " " + hrd.getHname() + " "
										+ hrd.getHemail() + " " + hrd.getHphone() + " " + hrd.getHpassword()));
							} else {
								System.out.println("In Hr " + hrname + " Match not found 🤷‍♂️");
							}
							System.out.println("===================");
							break;
						case 11:
							sc.nextLine();
							boolean contUpdate3 = true;
							while (contUpdate3) {
								List<CandidateModule> candidate = intvService.getCandidatesWithoutInterview();
								if (candidate.isEmpty()) {
									System.out.println("All candidates have been scheduled for an interview.");
									break;
								} else {
									System.out.println("Candidates without scheduled interviews:");
									System.out.println("======================================");
									candidate.forEach(candid -> System.out.println("ID: " + candid.getCandidate_id()
											+ ", Name: " + candid.getName() + ", Email: " + candid.getEmail()
											+ ", Phone: " + candid.getPhone()));
								}
								System.out.println("======================================");

								System.out.println("Enter Candidate Id");
								int candidateId2 = sc.nextInt();
								sc.nextLine();
								System.out.println("Enter Interview Date(yyyy-mm-dd)");
								String dateInput = sc.next();
								DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

								LocalDate interviewDate = LocalDate.parse(dateInput, formatter);

								System.out.println("Enter Time Slot (1-8):");
								int timeSlot = sc.nextInt();
								sc.nextLine();
								List<HRModel> availableHRs = intvService.getAvailableHRs(interviewDate, timeSlot);
								if (availableHRs.isEmpty()) {
									System.out.println("All HR have been scheduled for an interview.");
								} else {
									System.out.println("======================================");
									System.out.println("HR without scheduled interviews:");
									availableHRs.forEach(hra -> System.out.println("ID: " + hra.getHrid() + " Name: "
											+ hra.getHname() + " Email: " + " Phone: " + hra.getHphone()));
								}
								System.out.println("======================================");

								System.out.println("Enter HR ID:");
								int hrId = sc.nextInt();
								sc.nextLine();
								System.out.println("Enter Meeting Link");
								String meetingLink = sc.nextLine();
								if (intvService.scheduleInterview(hrId, candidateId2, interviewDate, timeSlot)) {
									System.out.println("Interview scheduled successfully.");
									String slot = "";
									switch (timeSlot) {
									case 1:
										slot = "10:00AM";
										break;
									case 2:
										slot = "10:30AM";
										break;
									case 3:
										slot = "11:00AM";
										break;
									case 4:
										slot = "11:30AM";
										break;
									case 5:
										slot = "12:00PM";
										break;
									case 6:
										slot = "12:30PM";
										break;
									case 7:
										slot = "02:00PM";
										break;
									case 8:
										slot = "03:00PM";
										break;
									}
									String message = "Dear Candidate, your interview is scheduled on " + interviewDate
											+ " at time slot " + slot
											+ ". Please use the following Google Meet link to join: " + meetingLink;
									boolean nset = intvService.isSendNotification(candidateId2, message, interviewDate);
									if (nset) {
										System.out.println("Interview Notifaction send");
									} else {
										System.out.println("Interview Notication not send");
									}
								} else {
									System.out.println("Failed to schedule interview.");
								}

								System.out.println(
										"Do you want to Shadule on other time or shadule more interview? yes/no 🤞");

								String response2 = sc.nextLine().trim().toLowerCase();
								if (!response2.equals("yes")) {
									contUpdate3 = false;
								}
							}
							break;

						case 12:
							System.out.println("😍😍 Thank You for Visit 😍😍");
							System.exit(0);
						default:
							System.out.println("Enter apropreate Choice 🤦‍♂️");
						}

					} while (true);

				} else {
					System.out.println("Some problem is there 🤦‍♂️");
				}
				break;

//---------------------------------HR Login-------------------------------------------------------------------				
			case 2:
				System.out.println("Enter HR Email id");
				sc.nextLine();
				String hemail = sc.nextLine();
				System.out.println("Enter Password");
				String hpass = sc.nextLine();

				Optional<Integer> optionalHrId = hrService.loginHR(hemail, hpass);
				if (optionalHrId.isPresent()) {
					int loggedInHrId = optionalHrId.get();
					System.out.println("Login Succesfull.......\n");
					System.out.println("===================");

					boolean contUpdate = true;

					while (contUpdate) {
						System.out.println("Enter apropreate Option");
						System.out.println("1:: Update Your Profile");
						System.out.println("2:: add Candidate");
						System.out.println("3:: Update Candidate");
						System.out.println("4:: Remove Candidate");
						System.out.println("5:: View Candidate's in database");
						System.out.println("6:: Search Candidate's by name");
						System.out.println("7:: Shadule Interview");
						System.out.println("Slect your choice");
						choice = sc.nextInt();
						sc.nextLine();
						System.out.println("===================");
						switch (choice) {
						case 1:
// -----------------------------------------HR Update---------------------------------------							
							System.out.println();
							boolean conUpdate = true;
							while (conUpdate) {
								System.out.println("Which fileld do you want to update?");
								System.out.println("Option: email, phone, password");
								String field = sc.nextLine().toLowerCase();
								String newValue;

								switch (field) {
								case "email":
									System.out.println("Enter new email");
									newValue = sc.nextLine();
									break;

								case "phone":
									System.out.println("Enter new phone");
									newValue = sc.nextLine();
									break;

								case "password":
									System.out.println("Enter new password");
									newValue = sc.nextLine();
									break;

								default:
									System.out.println("Invalid option. Please enter a valid field. 🤦‍♂️");
									continue;
								}

								boolean success = hrService.updateHRField(loggedInHrId, field, newValue);

								if (success) {
									System.out.println("your " + field + " updated successfully. 😊");
								} else {
									System.out.println(
											"Failed to update your details. Please check the HR ID or try again. 🤦‍♂️");
								}

								System.out.println("Do you want to update any thing else? yes/no 🤞");
								String response = sc.nextLine().trim().toLowerCase();
								if (!response.equals("yes")) {
									conUpdate = false;
								}
							}
							break;
//---------------------------------------Add Candidate---------------------------------------							
						case 2:

							System.out.println("Enter candidate Detail : ");
							System.out.println("Name:");
							String cname = sc.nextLine();
							System.out.println("Email:");
							String cemail = sc.nextLine();
							System.out.println("Phone :");
							String cphone = sc.nextLine();
							System.out.println("Address:");
							String caddress = sc.nextLine();
							System.out.println("Education:");
							String ceducation = sc.nextLine();
							System.out.println("Password");
							String cpassword = sc.nextLine();

							CandidateModule model = new CandidateModule(0, cname, cemail, cphone, caddress, ceducation,
									cpassword);
							boolean b3 = candidateService.isCandidateAdd(model);

							if (b3) {
								System.out.println(" Candidate Added Successfull! 😊");
							} else {
								System.out.println("Failed to add candidate. 🤦‍♂️");
							}
							break;

//---------------------------------Update Candidate Detail----------------------------------							
						case 3:
							System.out.println("Enter Candidate ID to update :");
							int candidateid = sc.nextInt();
							sc.nextLine();

							boolean contUpdate2 = true;

							while (contUpdate2) {
								System.out.println("Which fileld do you want to update?");
								System.out.println("Option: name, email, phone, address, education, password");
								String field = sc.nextLine().toLowerCase();

								String newValue;

								switch (field) {
								case "name":
									System.out.println("Enter new name : ");
									newValue = sc.nextLine();
									break;

								case "email":
									System.out.println("Enter new email : ");
									newValue = sc.nextLine();
									break;

								case "phone":
									System.out.println("Enter new phone number : ");
									newValue = sc.nextLine();
									break;
								case "address":
									System.out.println("Enter new address");
									newValue = sc.nextLine();
									break;
								case "education":
									System.out.println("Enter new education");
									newValue = sc.nextLine();
									break;
								case "password":
									System.out.println("Enter new password : ");
									newValue = sc.nextLine();
									break;
								default:
									System.out.println("Invalid option. Please enter a valid field.");
									continue;
								}

								boolean success = candidateService.updateCandidateField(candidateid, field, newValue);

								if (success) {
									System.out.println("Candidate " + field + " updated successfully. 😊");
								} else {
									System.out.println(
											"Failed to update HR details. Please check the HR ID or try again. 🤦‍♂️");
								}

								// if hr want to update more field
								System.out.println("Do you want to update any thing else? yes/no 🤞");
								String response = sc.nextLine().trim().toLowerCase();
								if (!response.equals("yes")) {
									contUpdate2 = false;
								}

							}

							break;
//----------------------------------Remove candidate By HR-----------------------------------------							
						case 4:
							System.out.println("Enter candidate id to remove");
							int candidateId = sc.nextInt();
							sc.nextLine();

							b = candidateService.isRemiveCandidate(candidateId);
							if (b) {
								System.out.println("Candidate is removed from list 😊");
							} else {
								System.out.println("Some problem is there... 🤦‍♂️");

							}
							break;
//-----------------------------------View Candidate details----------------------------------							
						case 5:
							System.out.println("===================");
							Optional<List<CandidateModule>> o = candidateService.getAllCandidate();
							if (o.isPresent()) {
								List<CandidateModule> list = o.get();
								list.forEach((can) -> System.out.println(can.getCandidate_id() + " " + can.getName()
										+ " " + can.getEmail() + " " + can.getPhone() + " " + can.getAddress() + " "
										+ can.getEducation() + " " + can.getPassword()));
							} else {
								System.out.println("In HR Section there is no data 🤷‍♂️");
							}

							break;
//------------------------------Search Candidate By name---------------------------------
						case 6:
							System.out.println("Enter the name to search for a candidate:");
							String cName = sc.nextLine().trim();
							System.out.println("===================");
							Optional<List<CandidateModule>> candidates = candidateService.searchCandidatesByName(cName);
							if (candidates.isPresent() && !candidates.get().isEmpty()) {
								List<CandidateModule> list = candidates.get();
								list.forEach((can) -> System.out.println(can.getCandidate_id() + " " + can.getName()
										+ " " + can.getEmail() + " " + can.getPhone() + " " + can.getAddress() + " "
										+ can.getEducation() + " " + can.getPassword()));
							} else {
								System.out.println("In Candidate Section there is no data like " + cName + " 🤷‍♂️");
							}
							break;
//-------------------------------------------Interview Schedule by HR-----------------------							
						case 7:
							
							boolean contUpdate3 = true;
							while (contUpdate3) {
								List<CandidateModule> candidate = intvService.getCandidatesWithoutInterview();
								if (candidate.isEmpty()) {
									System.out.println("All candidates have been scheduled for an interview.");
									break;
								} else {
									System.out.println("Candidates without scheduled interviews:");
									System.out.println("======================================");
									candidate.forEach(candid -> System.out.println("ID: " + candid.getCandidate_id()
											+ ", Name: " + candid.getName() + ", Email: " + candid.getEmail()
											+ ", Phone: " + candid.getPhone()));
								}
								System.out.println("======================================");

								System.out.println("Enter Candidate Id");
								int candidateId2 = sc.nextInt();
								sc.nextLine();
								System.out.println("Enter Interview Date(yyyy-mm-dd)");
								String dateInput = sc.next();
								DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

								LocalDate interviewDate = LocalDate.parse(dateInput, formatter);

								System.out.println("Enter Time Slot (1-8):");
								int timeSlot = sc.nextInt();
								sc.nextLine();
								List<HRModel> availableHRs = intvService.getAvailableHRs(interviewDate, timeSlot);
								if (availableHRs.isEmpty()) {
									System.out.println("All HR have been scheduled for an interview.");
								} else {
									System.out.println("======================================");
									System.out.println("HR without scheduled interviews:");
									availableHRs.forEach(hra -> System.out.println("ID: " + hra.getHrid() + " Name: "
											+ hra.getHname() + " Email: " +hra.getHemail()+ " Phone: " + hra.getHphone()));
								}
								System.out.println("======================================");

								System.out.println("Enter HR ID:");
								int hrId = sc.nextInt();
								sc.nextLine();
								System.out.println("Enter Meeting Link");
								String meetingLink = sc.nextLine();
								
								if (intvService.scheduleInterview(hrId, candidateId2, interviewDate, timeSlot)) {
									System.out.println("Interview scheduled successfully.");
									String slot = "";
									switch (timeSlot) {
									case 1:
										slot = "10:00AM";
										break;
									case 2:
										slot = "10:30AM";
										break;
									case 3:
										slot = "11:00AM";
										break;
									case 4:
										slot = "11:30AM";
										break;
									case 5:
										slot = "12:00PM";
										break;
									case 6:
										slot = "12:30PM";
										break;
									case 7:
										slot = "02:00PM";
										break;
									case 8:
										slot = "03:00PM";
										break;
									}
									String message = "Dear Candidate, your interview is scheduled on " + interviewDate+ " at time slot " + slot + ". Please use the following Google Meet link to join: " + meetingLink;
									System.out.println(meetingLink);
									boolean nset = intvService.isSendNotification(candidateId2, message, interviewDate);
									if (nset) {
										System.out.println("Interview Notifaction send");
									} else {
										System.out.println("Interview Notication not send");
									}

								} else {
									System.out.println("Failed to schedule interview.");
								}

								System.out.println(
										"Do you want to Shadule on other time or shadule more interview? yes/no 🤞");

								String response2 = sc.nextLine().trim().toLowerCase();
								if (!response2.equals("yes")) {
									contUpdate3 = false;
								}
							}
							break;

						default:
							System.out.println("Invalid option. Please enter a valid field. 🤦‍♂️");
							continue;
						}
						System.out.println("Do you want to update any thing else? yes/no 🤞");

						String response = sc.nextLine().trim().toLowerCase();
						if (!response.equals("yes")) {
							contUpdate = false;
						}
					}

				}
				break;
//-----------------------------------Candidate Login----------------------------------------------------------------
			case 3:
				System.out.println("Enter Candidate email id");
				sc.nextLine();
				String cemail = sc.nextLine();
				System.out.println("Enter Password");
				String cpassword = sc.nextLine();

				Optional<Integer> optionalCandidateId = candidateService.loginCandidate(cemail, cpassword);

				if (optionalCandidateId.isPresent()) {
					int candidateId = optionalCandidateId.get();
					System.out.println("Login successfull...!");

					boolean countUpdateC = true;
					while (countUpdateC) {
						System.out.println("1: Update Your Profile");
						System.out.println("2: Add Skill");
						System.out.println("3: Update Skill");
						System.out.println("4: Notification");
						System.out.println("===================");

						int choiceOfc = sc.nextInt();

						switch (choiceOfc) {
//------------------------------Profile Update By Candidate----------------------------------						
						case 1:
							System.out.println("Which fileld do you want to update?");
							System.out.println("Option: phone, address, education,  password,");
							String field = sc.nextLine().toLowerCase();
							String newValue;
							boolean countUpdateCf = true;
							while (countUpdateCf) {

								switch (field) {
								case "phone":
									System.out.println("Enter new phone number");
									newValue = sc.nextLine();

									break;
								case "address":
									System.out.println("Enter address");
									newValue = sc.nextLine();

									break;
								case "education":
									System.out.println("Enter heighest education");
									newValue = sc.nextLine();
									break;
								case "password":
									System.out.println("Enter password");
									newValue = sc.nextLine();
									break;
								default:
									System.out.println("Invalid option. Please enter a valid field.");
									continue;
								}

								boolean success = candidateService.updateCandidateField(candidateId, field, newValue);

								if (success) {
									System.out.println("Candidate " + field + " updated successfully.😍");
								} else {
									System.out.println(
											"Failed to update HR details. Please check the HR ID or try again. 🤦‍♂️");
								}

								System.out.println("Do you want to update any thing else? yes/no 🤞");
								String response = sc.nextLine().trim().toLowerCase();
								if (!response.equals("yes")) {
									countUpdateCf = false;
								}
							}

							break;
						case 2:
							sc.nextLine();
							String skill;

							System.out.println("Enter Skill");
							skill = sc.nextLine();

							System.out.println("Enter passout year");
							int passOutYear = sc.nextInt();
							sc.nextLine();

							candidateskills skillModel = new candidateskills('0', candidateId, skill, passOutYear);

							boolean b4 = candService.isAddSkilll(skillModel);
							if (b4) {
								System.out.println("Skill " + skill + " and passout year " + passOutYear
										+ " added successfully 😎");
							} else {
								System.out.println("Skill is not added 🤦‍♂️");
							}

							break;
						case 3:
							sc.nextLine();

							System.out.println("Enter Skill");
							skill = sc.nextLine();

							System.out.println("Enter passout year");
							passOutYear = sc.nextInt();
							sc.nextLine();

							skillModel = new candidateskills('0', candidateId, skill, passOutYear);

							b4 = candService.isAddSkilll(skillModel);
							if (b4) {
								System.out.println("Skill " + skill + " and passout year " + passOutYear
										+ " added successfully 😎");
							} else {
								System.out.println("Skill is not added 🤦‍♂️");
							}
							break;
						case 4:

							break;
						default:
							System.out.println("Enter apropreate Choice");
							continue;
						}

						System.out.println("Do you want to update any thing else? yes/no 🤞");
						String response = sc.nextLine().trim().toLowerCase();
						if (!response.equals("yes")) {
							countUpdateC = false;
						}
					}
				} else {
					System.out.println("Login failed invalid email or password 🤦‍♂️");
				}

				break;

			case 4:
				System.exit(0);

				break;

			default:
				System.out.println("Enter apropreate Choice 🤦‍♂️");
			}

		} while (true);
	}

}
