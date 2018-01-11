package com.perscab.model;

public class Account {

    private int id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private AccountStatus status;
    private String email;
    private String address;

    private Account() {
    }

//    public Account(int id) {
//        this.id = id;
//    }
//
//    public Account(int id, String login, String password, AccountStatus status) {
//        this(id);
//        this.login = login;
//        this.password = password;
//        this.status = status;
//    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static AccountBuilder newBuilder() {
        return new Account().new AccountBuilder();
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", status=" + status +
                '}';
    }

    public class AccountBuilder {

        private AccountBuilder() {
        }

        public AccountBuilder setId(int id) {
            Account.this.id = id;
            return this;
        }

        public AccountBuilder setLogin(String login) {
            Account.this.login = login;
            return this;
        }

        public AccountBuilder setPassword(String password) {
            Account.this.password = password;
            return this;
        }

        public AccountBuilder setStatus(AccountStatus status) {
            Account.this.status = status;
            return this;
        }

        public AccountBuilder setFirstName(String firstName) {
            Account.this.firstName = firstName;
            return this;
        }

        public AccountBuilder setLastName(String lastName) {
            Account.this.lastName = lastName;
            return this;
        }

        public AccountBuilder setEmail(String email) {
            Account.this.email = email;
            return this;
        }

        public AccountBuilder setAddress(String address) {
            Account.this.address = address;
            return this;
        }

        public Account build() {
            return Account.this;
        }
    }
}
