var app = new Vue({
    el: '#newapp',
    data: {
        loggedIn: false,
        user: {
            username: "",
            name: "",
            password: "",
            phone: "",
            subscribtionName: [],
            joinDate: "",
            credits: "",
            isAdmin: ""
        }
    },
    methods: {

        logIn: function () {

            this.loggedIn = true,
                this.user.name = "Mare",
                this.user.username = "mawence",
                this.user.phone = "1234567",
                this.user.joinedDate = "6.20.2018",
                this.user.password = "password",
                this.user.credits = "100",
                this.user.isAdmin = false;
        },
        logOut: function () {
            this.loggedIn = false,
                this.user = ""
        }
    }

})
