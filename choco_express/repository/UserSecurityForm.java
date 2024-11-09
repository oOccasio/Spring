package dgu.choco_express.repository;

import dgu.choco_express.domain.user.ERole;
import dgu.choco_express.domain.user.User;

public interface UserSecurityForm {
    Long getId();
    ERole getRole();

    static UserSecurityForm invoke(User user){
        return new UserSecurityForm() {
            @Override
            public Long getId() {
                return user.getId();
            }

            @Override
            public ERole getRole() {
                return user.getRole();
            }
        };
    }
}