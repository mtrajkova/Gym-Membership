Vue.component('mynavbar', {
    template: `
<div>
    <!--Navbar begin-->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
        <div class="container">
            <a class="navbar-brand" href="#">Capitol Fitness</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                    aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarResponsive">
                <ul class="navbar-nav ml-auto" v-if="!loggedIn">
                    <li class="nav-item">
                        <a href="#" class="text-white nav-link" data-target="#myModal"
                           data-toggle="modal">Register</a>
                    </li>
                    <li class="nav-item">
                        <a href="#" class="text-white nav-link" data-target="#loginModal" data-toggle="modal">Log in</a>
                    </li>
                </ul>

                <ul class="navbar-nav ml-auto" v-else>
                    <li class="nav-item active">
                        <a class="nav-link" href="#">Home
                            <span class="sr-only">(current)</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">About</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Services</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Contact</a>
                    </li>
                    <li class="nav-item">
                        <a class="text-white nav-link">Hello {{user.name}}</a>
                    </li>
                    <li class="nav-item">
                        <a href="#" class="text-white nav-link" @click="logOut()">Log out</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <!--Navbar end-->
    <!--Begin Login Modal-->
    <div class="modal" id="loginModal">
        <div class="modal-dialog">
            <div class="modal-content">

                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">Enter login credentials</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <!-- Modal body -->
                <div class="modal-body">
                        <p>
                            <label>Username:</label>
                            <input type="text" v-model="usernameLogin">
                        </p>
                        <p>
                            <label>Password:</label>
                            <input type="password" v-model="userPassword">
                        </p>
                </div>

                <!-- Modal footer -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" @click="logIn($event, usernameLogin, userPassword)">
                        Login
                    </button>
                    <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                </div>

            </div>
        </div>
    </div>-
    <!--End Login Modal-->
</div>
        `,
    data: function(){
        return {
            user: this.user,
            loggedIn: false,
            usernameLogin: null,
            userPassword: null
        }
    },
    // props: ['loggedIn', 'usernameLogin', 'userPassword'],
    methods: {
        logIn: function ($event, usernameLogin, userPassword) {
            var self = this;

            $.ajax({
                url: 'http://localhost:8080/login',
                type: 'post',
                data: {
                    username: usernameLogin,
                    password: userPassword
                },
                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                success: function (val) {
                    self.user = val;
                    self.loggedIn = true;
                    console.log(val);
                    console.log(self.loggedIn);
                    self.usernameLogin = "";
                    self.userPassword = "";
                    self.$parent.user = val;
                },
                error: function (XMLHttpRequest, status, error) {
                    alert("Incorrect credentials")
                }
            })
        },
        logOut: function () {
            this.loggedIn = false,
                this.user.name = "",
                this.user.username = "",
                this.user.phone = "",
                this.user.joinedDate = "",
                this.user.password = "",
                this.user.credits = "",
                this.user.isAdmin = false,
                this.user.dateJoined = {}
        }
    }
})

Vue.component('admin-users', {
    template:`

            <div class="col-5">
                    <h3 class="my-4 pt-5">Users</h3>
                <ul class="list-group">
                    <li class="list-group-item">user 1</li>
                    <li class="list-group-item">user 1</li>
                    <li class="list-group-item">user 1</li>
                    <li class="list-group-item">user 1</li>
                    <li class="list-group-item">user 1</li>
                </ul>
            </div>
            <div class="col-4">
                <h3 class="my-4 pt-5">Details</h3>
                <ul class="list-group">
                    <li class="">Name: {{ user.name }}</li>
                    <li class="">Username: {{user.username}}</li>
                    <li class="">Phone :{{user.phone}}</li>
                    <li class="">Date joined:
                        {{user.dateJoined.dayOfMonth}}.{{user.dateJoined.monthValue}}.{{user.dateJoined.year}}
                    </li>
                    <li class="">Credits: {{user.credits}}</li>
                    <li class="">Admin: {{user.isAdmin}}</li>
                </ul>
            </div>
   
`
})

var app = new Vue({
    el: '#app',
    data: {
        loggedIn: false,
        usernameLogin: "",
        userPassword: "",
        user: {
            username: "",
            name: "",
            password: "",
            phone: "",
            dateJoined: {},
            joinDate: "",
            credits: "",
            isAdmin: "",
            admin: ""
        },
        postBody: "",
        offers: [
            {
                id: 1,
                title: "ponuda 1",
                text: "ovaa ponuda vazhi samo vo period od 23 juni do 23 juli, zemete sega ili nikogash poekje!"
            },
            {
                id: 2,
                title: "ponuda 2",
                text: "ovaa ponuda vazhi samo vo period od 23 juni do 23 juli, zemete sega ili nikogash poekje!"
            }
        ],
        news: [
            {
                id: 1,
                title: "The machine for leg curls is broken",
                text: "due to malfunctions, the machine will we be out of work for 1 week",
                date: null
            }
        ],
        myWorkouts: [
            {
                date: "23.07.2018"
            },
            {
                date: "25.07.2018"
            }
        ],
        mySubscriptions: []
    },
    methods: {
        fillDate: function () {
            this.news.date = "2"
        },

        sendData: function (event, username) {
            /*axios.post('http://localhost:8080/register',{
                username: 'email',
                name: 'name',
                password: 'qwer',
                phone: '123'
            }, {'Content-Type': 'application/x-www-form-urlencoded'})
                .then(function (response) {
                    console.log('saved successfully')
                }).catch(e => {
                console.log(e.message)
            });*/
            /*axios({
                method: 'post',
                url: 'http://localhost:8080/register',
                data: {
                    username: 'email',
                    name: 'name',
                    password: 'qwer',
                    phone: '12345'
                },
                headers: {'Content-Type': 'application/x-www-form-urlencoded'}
            })*/
            /* axios.post('http://localhost:8080/register', {
                 usrename : 'mare',
                     password: '123'
              })*/
            $.ajax({
                url: 'http://localhost:8080/register',
                type: 'post',
                data: {
                    username: 'email',
                    name: 'name',
                    password: 'qwer',
                    phone: '123'
                },
                headers: {'Content-Type': 'application/x-www-form-urlencoded'}
            }).done(function (val) {
                console.log(val);
            })
        },
        getUsers: function () {
            axios.get('http://localhost:8080/users')
                .then(function (response) {

                    console.log(response.data); // ex.: { user: 'Your User'}
                    console.log(response.status); // ex.: 200
                    this.users = response.data;
                });
        }

    },
    mounted() {
        var self = this
        axios.get('http://localhost:8080/users')
            .then(function (response) {

                console.log(response.data); // ex.: { user: 'Your User'}
                console.log(response.status); // ex.: 200
                self.users = response.data;
            }).catch(function (error) {
            console.log(error);

        });

    }


})
