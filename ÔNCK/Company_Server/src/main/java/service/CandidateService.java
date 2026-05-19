package service;

import dao.CandidateDAO;

import java.util.List;

public class CandidateService {
    private final CandidateDAO candidateDAO= new CandidateDAO();
    public List<Object[]> findBySkillInOpenJobs(String skill){
        return  candidateDAO.findBySkillInOpenJobs(skill);
    }
}
