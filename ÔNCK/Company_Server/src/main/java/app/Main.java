package app;

import dto.JobDTO;
import entity.Candidate;
import entity.JobStatus;
import service.CandidateService;
import service.JobService;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Main {
    static void main() {
        JobService jobService = new JobService();
        JobDTO jobDTO = JobDTO.builder()
                .id("33223")
                .title("sadasd")
                .salary(3213141)
                .companyId("C002")
                .status(JobStatus.OPEN)
                .description("sadsada")
                .build();
//        if(jobService.addJob(jobDTO)){
//            System.out.println("Them o so ke");
//        }
//        JobDTO jobDTO1= JobDTO.builder().id("J001").salary(213213213).build();
//        if(jobService.updateJob(jobDTO1)){
//            System.out.println("Update thanh cong");
//        }
//        if(jobService.deleteJob("33223")){
//            System.out.println("Xoa thanh cong");
//        }
        CandidateService candidateService = new CandidateService();
        List<Object[]> candidate= candidateService.findBySkillInOpenJobs("Java");
        for(Object[] obj: candidate){
            System.out.println(obj[0]+"    :   "+obj[1]+  "    :    "  +obj[2]);
        }
        Map<JobDTO, Long> map = jobService.countPerJobByCompany("VNG Corporation");
        map.forEach((jobDTO1, aLong) -> System.out.println(jobDTO1+":::::::::::"+aLong));
    }
}
