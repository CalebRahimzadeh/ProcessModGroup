package csc360.doctor_office_simulator.models;
//abstraction
public class DoctorOffice {
	private Resource doctor;
	
	public DoctorOffice(int numOfDoctors) {
		this.doctor = new Doctor();	
	}
	
	public Resource getDoctor(){
		return doctor;
	}
	
	
}
