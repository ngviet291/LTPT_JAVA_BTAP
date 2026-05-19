package service;

import dao.CompanyDAO;
import dao.JobDAO;
import dto.JobDTO;
import entity.Company;
import entity.Job;

import java.util.LinkedHashMap;
import java.util.Map;

public class JobService {
    private final JobDAO jobDAO= new JobDAO();

    public boolean addJob(JobDTO jobDTO){
        CompanyDAO companyDAO= new CompanyDAO();
        Company company = companyDAO.findById(jobDTO.getCompanyId());
        Job job = Job.builder().id(jobDTO.getId())
                .title(jobDTO.getTitle())
                .description(jobDTO.getDescription())
                .salary(jobDTO.getSalary())
                .company(company)
                .status(jobDTO.getStatus())
                .build();
        return jobDAO.addJob(job);
    }
    public boolean updateJob(JobDTO jobDTO){
        Job job = Job.builder()
                .id(jobDTO.getId())
                .salary(jobDTO.getSalary())
                .build();
        return jobDAO.updateJob(job);
    }
    public boolean deleteJob(String id){
        return  jobDAO.deleteJob(id);
    }
    public Map<JobDTO, Long> countPerJobByCompany(String companyName){
        Map<JobDTO,Long> map = new LinkedHashMap<>();
        jobDAO.countPerJobByCompany(companyName).forEach((job, aLong) -> map.put(toDTO(job),aLong));
        return map;
    }
    public JobDTO toDTO(Job job){
        return  JobDTO.builder().id(job.getId()).salary(job.getSalary())
                .title(job.getTitle())
                .companyId(job.getCompany().getId())
                .description(job.getDescription())
                .status(job.getStatus()).build();
    }
}
