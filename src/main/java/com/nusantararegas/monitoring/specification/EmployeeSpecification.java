package com.nusantararegas.monitoring.specification;

import com.nusantararegas.monitoring.dtos.SearchEmployeeRequest;
import com.nusantararegas.monitoring.entity.Employee;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class EmployeeSpecification {

    public static Specification<Employee> getSpecification(SearchEmployeeRequest request) {

        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            // Filter fullName LIKE %fullName%
            if (request.getFullName() != null && !request.getFullName().isEmpty()) {
                Predicate fullNamePredicate = criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("fullName")),
                        "%" + request.getFullName().toLowerCase() + "%"
                );
                predicates.add(fullNamePredicate);
            }

            // Filter jobTitle LIKE %jobTitle%
            if (request.getJobTitle() != null && !request.getJobTitle().isEmpty()) {
                Predicate jobTitlePredicate = criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("jobTitle")),
                        "%" + request.getJobTitle().toLowerCase() + "%"
                );
                predicates.add(jobTitlePredicate);
            }

            return query.where(predicates.toArray(new Predicate[0])).getRestriction();
        };
    }
}
