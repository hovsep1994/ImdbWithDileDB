package com.aca.imdb.user;

public class AdminRepository extends UserRepository<Admin> {

    private static final AdminRepository ADMIN_REPOSITORY = new AdminRepository();

    private AdminRepository() {
        super(Admin.class);
    }

    public static AdminRepository getInstance() {
        return ADMIN_REPOSITORY;
    }

    @Override
    public Admin getById(Long id) {
        return (Admin) super.getById(id);
    }

    @Override
    public Admin getUser(String username, String password) {
        return (Admin) super.getUser(username, password);
    }

    @Override
    public Admin create(Admin admin) {
        if (isUnique(admin.getUsername())) {
            return super.create(admin);
        }
        throw new IllegalArgumentException("Username already exists");
    }
}
