/*
import VueSession from 'vue-session'
Vue.use(VueSession);
*/

Vue.component('navigation', {
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
                        <a href="#" class="text-white nav-link" data-target="#registerModal"
                           data-toggle="modal">Register</a>
                    </li>
                    <li class="nav-item">
                        <a href="#" class="text-white nav-link" data-target="#loginModal" data-toggle="modal">Log in</a>
                    </li>
                </ul>

                <ul class="navbar-nav ml-auto" v-else>
                    <li class="nav-item">
                        <span class="nav-link active">Hello {{user.name}}</span>
                    </li>
                    <!--<li class="nav-item">
                        <a class="nav-link" href="#" @click="selectedTab = Home" >Home
                            &lt;!&ndash;<span class="sr-only">(current)</span>&ndash;&gt;
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#" @click="selectedTab = ProfilePage" :class="{activeTab: selectedTab === ProfilePage}">Profile page</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#" @click="selectedTab = Forum" :class="{activeTab: selectedTab === Forum}">Forum</a>
                    </li>
                    <li class="nav-item" v-show="admin">
                        <a class="nav-link" href="#" @click="selectedTab = AdminPage" :class="{activeTab: selectedTab === AdminPage}">Admin page</a>
                    </li>-->
                    <li class="nav-link" :class="{ activeTab: selectedTab === tab}"
                    v-for="(tab, index) in tabs"
                    :key="index"
                    @click="selectedTab = tab"
                    v-if="showAdminPage(tab)">{{ tab }}</li>
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
                            <input class="inputfield" type="text" v-model="usernameLogin">
                        </p>
                        <p>
                            <label>Password:</label>
                            <input class="inputfield" type="password" v-model="userPassword">
                        </p>
                </div>

                <!-- Modal footer -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" @click="logIn($event, usernameLogin, userPassword)" data-dismiss="modal">
                        Login
                    </button>
                    <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                </div>

            </div>
        </div>
    </div>-
    <!--End Login Modal-->
    
    <!--Begin Register Modal-->
    <div class="modal" id="registerModal">
        <div class="modal-dialog">
            <div class="modal-content">

                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">Enter information</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <!-- Modal body -->
                <div class="modal-body">
                        <p>
                            <label>Username:</label>
                            <input class="inputfield" type="text" v-model="usernameLogin">
                        </p>
                        <p>
                            <label>Password:</label>
                            <input class="inputfield" type="password" v-model="userPassword">
                        </p>
                        <p>
                            <label>Name:</label>
                            <input class="inputfield" type="text" v-model="userName">
                        </p>
                        <p>
                            <label>Telephone:</label>
                            <input class="inputfield" type="tel" v-model="telephone">
                        </p>
                </div>

                <!-- Modal footer -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" @click="register($event, usernameLogin, userPassword, userName, telephone)" data-dismiss="modal">
                        Register
                    </button>
                    <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                </div>

            </div>
        </div>
    </div>-
    <!--End Register Modal-->
    
    <homepage v-show="selectedTab === 'Home'"></homepage>
    
    <admin-page-component v-show="selectedTab === 'Admin Page'"></admin-page-component>
    
</div>
        `,
    data: function () {
        return {
            user: this.user,
            loggedIn: false,
            userName: null,
            telephone: null,

            usernameLogin: null,
            userPassword: null,
            admin: false,
            tabs: ['Home', 'Profile Page', 'Forum', 'Admin Page'],
            selectedTab: 'Home'
        }
    },
    // props: ['loggedIn', 'usernameLogin', 'userPassword'],
    methods: {
        showAdminPage: function (tab) {
            if (tab === "Admin Page" && this.admin) {
                return true;
            }
            else if (tab !== "Admin Page") {
                return true;
            } else {
                return false;
            }
        },
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
                    self.admin = val.admin;
                    self.$parent.user = val;
                    document.getElementsByClassName("inputfield").value = "";
                    console.log("success");
                    /* this.$session.start();
                     this.$session.set('user', val)*/
                },
                error: function (XMLHttpRequest, status, error) {
                    alert("Incorrect credentials")
                }
            })
        },
        register: function ($event, usernameLogin, userPassword, userName, telephone) {
            var self = this;

            $.ajax({
                url: 'http://localhost:8080/register',
                type: 'post',
                data: {
                    username: usernameLogin,
                    password: userPassword,
                    phone: telephone,
                    name: userName
                },
                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                success: function (val) {
                    self.user = val;
                    self.loggedIn = true;
                    console.log(val);
                    console.log(self.loggedIn);
                    self.usernameLogin = "";
                    self.userPassword = "";
                    self.admin = val.admin;
                    self.$parent.user = val;
                    document.getElementsByClassName("inputfield").value = "";
                    console.log("success")
                },
                error: function (XMLHttpRequest, status, error) {
                    if (XMLHttpRequest.responseText == "Client already exists") {
                        alert(XMLHttpRequest.responseText)
                    }
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


//  COMPONENTS FOR HOME PAGE
Vue.component('offers', {
    template: `
    <div class="row">
                   <div class="col-4" v-for="subscription in subscriptions">
                    <div class="card ">
                        <div class="card-title ml-4 mt-4 font-weight-bold">{{subscription.name}}</div>
                        <div class="card-body">Duration of subscription: {{subscription.durationMonths}} month(s)</div>
                        <div class="card-footer">
                            <button class="btn btn-success">Take offer only for {{subscription.price}} MKD!</button>
                        </div>
                    </div>
                    </div>
        </div>
    `,
    data: function () {
        return {
            subscriptions: []
        }
    },
    mounted() {
        var self = this
        axios.get('http://localhost:8080/subscriptions')
            .then(function (response) {
                console.log(response.data); // ex.: { user: 'Your User'}
                console.log(response.status); // ex.: 200
                self.subscriptions = response.data;
            }).catch(function (error) {
            console.log(error);

        });
    }

})

Vue.component('news', {
    template: `
     <!--News-->

        <div class="row">
            <div class="col-4 " v-for="n in news">
                    <div class="card">
                        <div class="card-title ml-4 mt-4 font-weight-bold">{{n.title}}</div>
                        <div class="card-body">{{n.text}}</div>
                        <div class="card-footer"><button class="btn btn-primary">Leave a comment</button></div>
                        <!--<div class="card-footer" v-bind:class="fillDate()"></div>-->
                    </div>
            </div>
        </div>
    `,
    data: function () {
        return {
            news: []
        }

    },
    mounted() {
        var self = this;
        axios.get('http://localhost:8080/myPosts/byAdmin')
            .then(function (response) {
                console.log(response.data); // ex.: { user: 'Your User'}
                console.log(response.status); // ex.: 200
                self.news = response.data;
            }).catch(function (error) {
            console.log(error);

        });
    }
})

Vue.component('homepage', {
    template: `
    <div class="container">

        <!--Carousel-->

        <div id="demo" class="pt-5 carousel slide" data-ride="carousel">

            <!-- Indicators -->
            <ul class="carousel-indicators">
                <li data-target="#demo" data-slide-to="0" class="active"></li>
                <li data-target="#demo" data-slide-to="1"></li>
                <li data-target="#demo" data-slide-to="2"></li>
            </ul>

            <!-- The slideshow -->
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <img src="images/slika1.jpg" alt="Los Angeles" class="img-fluid w-100 h-50">
                </div>
                <div class="carousel-item">
                    <img src="images/slika2.jpg" alt="Chicago" class="img-fluid w-100 h-50" >
                </div>
                <div class="carousel-item">
                    <img src="images/sllika3.jpg" alt="New York" class="img-fluid w-100 h-50">
                </div>
            </div>

            <!-- Left and right controls -->
            <a class="carousel-control-prev" href="#demo" data-slide="prev">
                <span class="carousel-control-prev-icon"></span>
            </a>
            <a class="carousel-control-next" href="#demo" data-slide="next">
                <span class="carousel-control-next-icon"></span>
            </a>

        </div>
        <!--End Carousel-->

        <h1 class="my-4 ">Our newest subscriptions
            <small></small>
        </h1>

        <offers></offers>
        <br/>
        <a class="py-2" href="#">See all subscriptions</a>
        <h1 class="my-4 ">Admin posts
            <small></small>
        </h1>
        <news></news>


    </div>

    `
})

// END COMPONENTS FOR HOME PAGE


// COMPONENTS FOR ADMIN PAGE

Vue.component('admin-user-details', {
    template: `
            <div v-show="selected">
                <h3 class="my-4 pt-5">Details</h3>
                <ul class="list-group list-unstyled">
                    <li class="">Name: {{ user.name }}</li>
                    <li class="">Username: {{user.username}}</li>
                    <li class="">Phone :{{user.phone}}</li>
                    <li class="">Date joined:
                        {{user.dateJoined.dayOfMonth}}.{{user.dateJoined.monthValue}}.{{user.dateJoined.year}}
                    </li>
                    <li class="">Credits: {{user.credits}}</li>
                    <li class="">Admin: {{user.admin}}</li>
                </ul>
            </div>
`,

    data: function () {
        return {
            selected: false,
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
            }

        }
    }
    ,
    mounted() {
        eventBus.$on('viewUser', user => {
            this.user = user;
            this.selected = true
        });

    }
})

Vue.component('admin-users', {
    template: `
            <div>
                <h3 class="my-4 pt-5">Users</h3>
                <ul class="list-group ">
                    <li class="list-group-item" v-for="user in users"><a href="#" @click="viewUser(user)">{{user.username}}</a></li>
                </ul>
            </div>
    `,
    data: function () {
        return {
            users: []
        }

    },
    methods: {
        viewUser: function (user) {
            eventBus.$emit('viewUser', user);
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

Vue.component('admin-subs', {
    template: `
            <div>
                <h3 class="my-4 pt-5">Subscriptions</h3>
                <ul class="list-group ">
                    <li class="list-group-item" v-for="sub in subs"><a href="#" @click="viewSub(sub)">{{sub.name}}</a></li>
                </ul>
            </div>
    `,
    data: function () {
        return {
            subs: []
        }

    },
    methods: {
        viewUser: function (sub) {
            eventBus.$emit('viewSub', sub);
        }
    },
    mounted() {
        var self = this
        axios.get('http://localhost:8080/subscriptions')
            .then(function (response) {
                console.log(response.data); // ex.: { user: 'Your User'}
                console.log(response.status); // ex.: 200
                self.subs = response.data;
            }).catch(function (error) {
            console.log(error);

        });
    }
})


Vue.component('admin-subs-details', {
    template: `
            <div v-show="selected">
                <h3 class="my-4 pt-5">Details</h3>
                <ul class="list-group list-unstyled">
                    <li class="">Name: {{ sub.name }}</li>
                    <li class="">Username: {{sub.price}}</li>
                    <li class="">Phone :{{user.phone}}</li>
                    <li class="">Date joined:
                        {{user.dateJoined.dayOfMonth}}.{{user.dateJoined.monthValue}}.{{user.dateJoined.year}}
                    </li>
                    <li class="">Credits: {{user.credits}}</li>
                    <li class="">Admin: {{user.admin}}</li>
                </ul>
            </div>
`,

    data: function () {
        return {
            selected: false,
            specialSub: {
                name: null,
                type: null,
                price: null,
                startOfRegistration: null,
                endOfRegistration: null,
                durationMonths: null
            },
            normalSub:{
                name: null,
                type: null,
                price: null,
                durationMonths: null
            }

        }
    }
    ,
    mounted() {
        eventBus.$on('viewSub', sub => {
            if(sub.type === 'special'){
                this.specialSub = sub;
            }
            else if(sub.type === 'normal')
            this.user = user;

            this.selected = true
        });

    }
})


Vue.component('admin-page-component', {
    template: `
<div class="container">
        <div class="row">
            <div class="col-3">
                <h1 class="my-4 pt-5">Admin page
                </h1>
                <a href="#" v-bind:class="viewClients ? activeClass : nonActiveClass" @click="enableClients">View clients</a>
                <a href="#" v-bind:class="viewSubs ? activeClass : nonActiveClass" @click="enableSubs">View subscriptions</a>
            </div>
            <div class="col-5">
                <admin-users v-show="viewClients"></admin-users>
                <admin-subs v-show="viewSubs"></admin-subs>
            </div>
            <div class="col-4">
                <admin-user-details v-show="enableClientDetails"></admin-user-details>
            </div>
        </div>
    </div>
`,
    data: function () {
        return {
            activeClass: 'btn btn-primary btn-block',
            nonActiveClass: 'btn btn-outline-primary btn-block',
            viewClients: false,
            viewSubs: false,
            selectedClient: false
        }
    },
    methods: {
        enableClients: function () {
            this.viewClients = true;
            this.viewSubs = false;
        },
        enableSubs: function () {
            this.viewSubs = true;
            this.viewClients = false;
            this.selectedClient = false;
        }
    },
    computed:{
        enableClientDetails: function () {
            return this.viewClients && this.selectedClient;
        }
    },
    mounted() {
        eventBus.$on('viewUser', user => {
            console.log(this.selectedClient);
            this.selectedClient = true;
            console.log(this.selectedClient)
        });

    }
})

// END COMPONENTS FOR ADMIN PAGE

var eventBus = new Vue()

var app = new Vue({
    el: '#app',
    data: {
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
        }
    }
})