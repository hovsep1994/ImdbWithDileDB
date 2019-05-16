package com.aca.imdb.user;

public class RegularUserRepository extends UserRepository<RegularUser> {

    private static final RegularUserRepository REPOSITORY = new RegularUserRepository();

    private RegularUserRepository() {
        super(RegularUser.class);
    }

    public static RegularUserRepository getInstance() {
        return REPOSITORY;
    }

    @Override
    public RegularUser getUser(String username, String password) {
        return (RegularUser) super.getUser(username, password);
    }

    @Override
    public RegularUser create(RegularUser user) {
        if (isUnique(user.getUsername())) {
            return super.create(user);
        }
        throw new IllegalArgumentException("Username is not unique");
    }
}
