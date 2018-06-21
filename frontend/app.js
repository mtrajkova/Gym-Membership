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
    <forum v-show="selectedTab === 'Forum'"></forum>
    
    <my-workouts v-show="selectedTab === 'Profile Page'"></my-workouts>
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
        passUsername: function() {
            eventBus4.$emit('userExercises', self.user.username);

        },
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

                    eventBus4.$emit('userExercises', val.username);
                    eventBus3.$emit('loggedUser', val);



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

                    eventBus3.$emit('loggedUser', val);
                    eventBus4.$emit('userExercises', val.username);
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
                    <li class="list-group-item" v-for="sub in normalSubs">
                        <a href="#">{{ sub.name }}</a><br/>
                        <a href="#">Price: {{ sub.price }}</a><br/>
                        <a href="#">For: {{ sub.durationMonths }} month(s)</a>
                        <a href="#">Is available: {{ sub.available }} </a> <button class="btn btn-sm btn-success" v-show="!sub.available" @click="changeAvailabilityNormal(sub)">Make available</button><button class="btn btn-sm btn-danger" @click="changeAvailabilityNormal(sub)" v-show="sub.available">Make unavailable</button>
                    </li>
                    <li class="list-group-item" v-for="sub in specialSubs">
                        <a href="#">{{ sub.name }}</a><br/>
                        <a href="#">Price: {{ sub.price }}</a><br/>
                        <a href="#">For: {{ sub.durationMonths }} month(s)</a><br/>
                        <a href="#">Start of registration: {{ sub.startOfRegistration }}</a><br/>
                        <a href="#">End of registration: {{ sub.endOfRegistration }}</a><br/>
                        <a href="#">Is available: {{ sub.available }} </a> <button class="btn btn-sm btn-success" v-show="!sub.available" @click="changeAvailabilitySpecial(sub)">Make available</button><button class="btn btn-sm btn-danger" @click="changeAvailabilitySpecial(sub)" v-show="sub.available">Make unavailable</button>
                    </li>
                    <li class="list-group-item" v-for="sub in workoutSub">
                        <a href="#">{{ sub.name }}</a><br/>
                        <a href="#">Price: {{ sub.price }}</a><br/>
                        <a href="#">For: {{ sub.numberOfDays }} days</a>
                        <a href="#">Is available: {{ sub.available }} </a> <button class="btn btn-sm btn-success" v-show="!sub.available" @click="changeAvailabilityWorkout(sub)">Make available</button><button class="btn btn-sm btn-danger" @click="changeAvailabilityWorkout(sub)" v-show="sub.available">Make unavailable</button>
                    </li>
                </ul>
            </div>
    `,
    data: function () {
        return {
            normalSubs: [],
            specialSubs: [],
            workoutSub: [],
        }

    },
    methods: {
        changeAvailabilitySpecial: function (sub) {
            var self = this;
            console.log(sub.name);
            $.ajax({
                url: 'http://localhost:8080/specialSubscriptions/changeAvailability',
                type: 'post',
                data: {
                    isAvailable: sub.available,
                    name: sub.name
                },
                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                success: function (val) {
                    self.user = val;
                    console.log(val);
                    console.log("success");
                    self.updateLists()
                },
                error: function (XMLHttpRequest, status, error) {
                    alert("error")
                }
            })
        },
        changeAvailabilityWorkout: function (sub) {
            var self = this;
            console.log(sub.name);
            $.ajax({
                url: 'http://localhost:8080/workoutSubscriptions/changeAvailability',
                type: 'post',
                data: {
                    isAvailable: sub.available,
                    name: sub.name
                },
                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                success: function (val) {
                    self.user = val;
                    console.log(val);
                    console.log("success");
                    self.updateLists()
                },
                error: function (XMLHttpRequest, status, error) {
                    alert("error")
                }
            })
        },
        changeAvailabilityNormal: function (sub) {
            var self = this;
            console.log(sub.name);
            $.ajax({
                url: 'http://localhost:8080/normalSubscriptions/changeAvailability',
                type: 'post',
                data: {
                    isAvailable: sub.available,
                    name: sub.name
                },
                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                success: function (val) {
                    self.user = val;
                    console.log(val);
                    console.log("success");
                    self.updateLists()
                },
                error: function (XMLHttpRequest, status, error) {
                    alert("error")
                }
            })
        },

        updateLists: function () {
            var self = this
            axios.get('http://localhost:8080/workoutSubscriptions')
                .then(function (response) {
                    console.log(response.data); // ex.: { user: 'Your User'}
                    console.log(response.status); // ex.: 200
                    self.workoutSub = response.data;
                }).catch(function (error) {
                console.log(error);

            });
            axios.get('http://localhost:8080/normalSubscriptions')
                .then(function (response) {
                    console.log(response.data); // ex.: { user: 'Your User'}
                    console.log(response.status); // ex.: 200
                    self.normalSubs = response.data;
                }).catch(function (error) {
                console.log(error);

            });
            axios.get('http://localhost:8080/specialSubscriptions')
                .then(function (response) {
                    console.log(response.data); // ex.: { user: 'Your User'}
                    console.log(response.status); // ex.: 200
                    self.specialSubs = response.data;
                }).catch(function (error) {
                console.log(error);

            });
        }
    },
    mounted() {
        var self = this
        axios.get('http://localhost:8080/workoutSubscriptions')
            .then(function (response) {
                console.log(response.data); // ex.: { user: 'Your User'}
                console.log(response.status); // ex.: 200
                self.workoutSub = response.data;
            }).catch(function (error) {
            console.log(error);

        });
        axios.get('http://localhost:8080/normalSubscriptions')
            .then(function (response) {
                console.log(response.data); // ex.: { user: 'Your User'}
                console.log(response.status); // ex.: 200
                self.normalSubs = response.data;
            }).catch(function (error) {
            console.log(error);

        });
        axios.get('http://localhost:8080/specialSubscriptions')
            .then(function (response) {
                console.log(response.data); // ex.: { user: 'Your User'}
                console.log(response.status); // ex.: 200
                self.specialSubs = response.data;
            }).catch(function (error) {
            console.log(error);

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
    computed: {
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

// COMPONENTS FOR FORUM PAGE

Vue.component('forum', {
    template: `
    <div class="container">
        <forum-posts v-show="viewPosts" ></forum-posts>    
        <post-details v-show="viewPostDetails"></post-details>
    </div>
    `,
    data: function () {
        return {
            viewPosts: true,
            viewPostDetails: false
        }
    },
    mounted() {
        eventBus2.$on('showPost', post => {
            this.viewPostDetails = true;
            this.viewPosts = false;
        });
        eventBus.$on('goBack', post => {
            this.viewPosts = true;
            this.viewPostDetails = false;
        });
    }
})

Vue.component('forum-posts', {
    template: `
<div class="row">
            <div class="col-2">
                <h1 class="my-4 pt-5">
               
            </div>
            <div class="col-5">
              <h3 class="my-4 pt-5">Posts</h3>
                <ul class="list-group ">
                    <li class="list-group-item" v-for="post in posts"><a href="#" @click="viewPost(post)" class="float-left">Post title: {{post.title}}</a><a class="float-right">Posted by: {{post.client.username}}</a></li>
                </ul>
            </div>
            <div class="col-5">

            </div>
        </div>
    `,
    data: function () {
        return {
            posts: []
        }

    },
    methods: {
        viewPost: function (post) {
            eventBus.$emit('postDetails', post);
            eventBus2.$emit('showPost', post)
        }
    },
    mounted() {
        var self = this
        axios.get('http://localhost:8080/myPosts/')
            .then(function (response) {
                console.log(response.data); // ex.: { user: 'Your User'}
                console.log(response.status); // ex.: 200
                self.posts = response.data;
            }).catch(function (error) {
            console.log(error);

        });
    }
})

Vue.component('post-details', {
    template: `
<div class="row">
            <div class="col-2">
                <h1 class="my-4 pt-5">
               <button class="btn btn-danger d-inline" @click="goBack()">Go back</button>
            </div>
            <div class="col-5">
              <h3 class="my-4 pt-5">Post title: {{ post.title }}</h3>
              <small>Posted by: {{ post.client.username }} </small>
              <p>{{ post.text }}</p>
                <ul class="list-group ">
                    <p v-if="!comments.length">There are no comments yet. Be the first one to leave a comment on this post!</p>
                    <div class="card " v-for="comment in comments" style="height: 100px;">
                        <div class="card-title font-weight-bold card-header">Posted by: {{ comment.client.username }}</div>
                        <div class="card-body" style="height: 100px;">{{ comment.text }}</div>
                        <div class="card-footer"><button class="btn-success btn" @click.once="updateUpvotes(comment)">Up {{comment.upvotes}}</button><button class="btn btn-danger" @click.once="updateDownvotes(comment)">Down {{comment.downvotes}}</button></div>
                    </div>
                </ul>
            </div>
            <div class="col-5">
             <h3 class="my-4 pt-5">Leave a comment</h3>
                <form class="review-form" @submit.prevent="onSubmit">
                  <p>
                    <textarea  id="commentText" v-model="commentText"></textarea>
                  </p>
                      
                  <p>
                    <input type="submit" class="btn btn-outline-success" value="Submit">  
                  </p>    
    
    </form>

            </div>
        </div>
    `,
    data: function () {
        return {
            post: null,
            comments: [],
            postTitle: "",
            user: null,
            commentText: "",

        }
    },
    methods: {
        onSubmit: function () {
            var self = this
            if (self.commentText.length > 0) {

                $.ajax({
                    url: 'http://localhost:8080/comments/addComment',
                    type: 'post',
                    data: {
                        text: self.commentText,
                        postTitle: self.post.title,
                        username: self.user.username
                    },
                    headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                    success: function (val) {
                        self.user = val;
                        console.log(val);
                        console.log("success");
                        self.getPostComments()
                    },
                    error: function (XMLHttpRequest, status, error) {
                        alert("error")
                    }
                });
                this.commentText = null
            }
            else {
                alert("Comment cannot be empty")
            }
        }
        ,
        goBack: function () {
            eventBus.$emit('goBack', self.post);
        },
        getPostComments: function () {
            var self = this
            axios.get('http://localhost:8080/comments/' + self.postTitle)
                .then(function (response) {
                    console.log(response.data); // ex.: { user: 'Your User'}
                    console.log(response.status); // ex.: 200
                    console.log("getting comments . " + self.postTitle);
                    self.comments = response.data;
                }).catch(function (error) {
                console.log(error);

            });
        },
        updateUpvotes: function (comment) {
            var self = this;
            comment.upvotes += 1;
            $.ajax({
                url: 'http://localhost:8080/comments/vote',
                type: 'post',
                data: {
                    text: comment.text,
                    title: self.post.title,
                    vote: "upvote"
                },
                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                success: function (val) {
                    self.user = val;
                    console.log(val);
                    console.log("success");
                    //   self.getPostComments()
                },
                error: function (XMLHttpRequest, status, error) {
                    alert("error")
                }
            })

        },
        updateDownvotes: function (comment) {
            var self = this;
            console.log(comment.title);
            comment.downvotes += 1;
            $.ajax({
                url: 'http://localhost:8080/comments/vote',
                type: 'post',
                data: {
                    text: comment.text,
                    title: self.post.title,
                    vote: "downvote"
                },
                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                success: function (val) {
                    self.user = val;
                    console.log(val);
                    console.log("success");
                    //  self.getPostComments()
                },
                error: function (XMLHttpRequest, status, error) {
                    alert("error")
                }
            })

        }
    },
    mounted() {
        eventBus.$on('postDetails', post => {
            this.post = post;
            this.postTitle = post.title;
            console.log("========= post =========");
            console.log(post.title)
            console.log(post);
            console.log("========= post =========");
            var self = this;
            axios.get('http://localhost:8080/comments/' + self.postTitle)
                .then(function (response) {
                    console.log(response.data); // ex.: { user: 'Your User'}
                    console.log(response.status); // ex.: 200
                    console.log("getting comments . " + self.postTitle);
                    self.comments = response.data;
                }).catch(function (error) {
                console.log(error);

            });
        });
        eventBus3.$on('loggedUser', val => {
            var self = this;
            self.user = val
        });


    }
})


// END COMPONENTS FOR FORUM PAGE

// COMPONENTS FOR MY PROfiLE PAGE


Vue.component('my-subs', {
    template: `
        <div >
        <h3 class="my-4 pt-5">Subscriptions</h3>
                <ul class="list-group ">
                    <li class="list-group-item" v-for="sub in normalSubs">
                        <a href="#">{{ sub.name }}</a><br/>
                        <a href="#">Price: {{ sub.price }}</a><br/>
                        <a href="#">For: {{ sub.durationMonths }} month(s)</a>
                    </li>
                    <li class="list-group-item" v-for="sub in specialSubs">
                        <a href="#">{{ sub.name }}</a><br/>
                        <a href="#">Price: {{ sub.price }}</a><br/>
                        <a href="#">For: {{ sub.durationMonths }} month(s)</a><br/>
                    </li>
                    <li class="list-group-item" v-for="sub in workoutSub">
                        <a href="#">{{ sub.name }}</a><br/>
                        <a href="#">Price: {{ sub.price }}</a><br/>
                        <a href="#">For: {{ sub.numberOfDays }} days</a>
                    </li>
                </ul>
</div>
    `,
    data: function () {
        return {
            normalSubs: [],
            specialSubs: [],
            workoutSub: [],
            username: ""
        }

    },
    methods: {
        checkUsername: function () {
            var self = this;
            if (self.username.length > 0) {
                self.updateLists();
                console.log("adfasf");
                console.log(self.username);
                return true;
            }
            else {
                return false;
            }
        },

        updateLists: function () {
            var self = this
            axios.get('http://localhost:8080/workoutSubscriptions')
                .then(function (response) {
                    console.log(response.data); // ex.: { user: 'Your User'}
                    console.log(response.status); // ex.: 200
                    self.workoutSub = response.data;
                }).catch(function (error) {
                console.log(error);

            });
            axios.get('http://localhost:8080/normalSubscriptions')
                .then(function (response) {
                    console.log(response.data); // ex.: { user: 'Your User'}
                    console.log(response.status); // ex.: 200
                    self.normalSubs = response.data;
                }).catch(function (error) {
                console.log(error);

            });
            axios.get('http://localhost:8080/specialSubscriptions')
                .then(function (response) {
                    console.log(response.data); // ex.: { user: 'Your User'}
                    console.log(response.status); // ex.: 200
                    self.specialSubs = response.data;
                }).catch(function (error) {
                console.log(error);

            });
        }
    },
    mounted() {
        var self = this
        eventBus5.$on('subscriptionEvent', username => {
            self.username = username

            axios.get('http://localhost:8080/myWorkoutSubscriptions/' + self.username)
                .then(function (response) {
                    console.log(response.data); // ex.: { user: 'Your User'}
                    console.log(response.status); // ex.: 200
                    self.workoutSub = response.data;
                }).catch(function (error) {
                console.log(error);

            });
            axios.get('http://localhost:8080/myNormalSubscriptions/' + self.username)
                .then(function (response) {
                    console.log(response.data); // ex.: { user: 'Your User'}
                    console.log(response.status); // ex.: 200
                    self.normalSubs = response.data;
                }).catch(function (error) {
                console.log(error);

            });
            axios.get('http://localhost:8080/mySpecialSubscriptions/' + self.username)
                .then(function (response) {
                    console.log(response.data); // ex.: { user: 'Your User'}
                    console.log(response.status); // ex.: 200
                    self.specialSubs = response.data;
                }).catch(function (error) {
                console.log(error);

            });
        })
    }
})

Vue.component('my-workouts', {
    template: `
<div class="container">
    <div class="row">
            <div class="col-6">
              <h3 class="my-4 pt-5">My workouts</h3>
                <ul class="list-group ">
                    <li class="list-group-item"  v-for="workout in workouts"><a href="#" @click="viewWorkout(workout)" class="float-left">{{ workout.workoutName }} {{ workout.date }}</a></li>
                </ul>
                <button class="btn btn-primary">Add workout</button>
            </div>
            <div class="col-6">
                <my-subs ></my-subs>
            </div>
        </div>
        </div>
    `,
    data: function () {
        return {
            workouts: [],
            username: null
        }
    },
    methods: {
        viewWorkout: function (workout) {
            eventBus3.$emit('workoutDetails', workout)
        },
        checkIfWorkouts: function () {
            var self = this;
            if (self.workouts.length === 0) {
                self.getWorkouts();
                return true;
            } else {
                return false;
            }
        },
        getWorkouts: function () {
            var self = this
            axios.get('http://localhost:8080/workouts/' + self.username)
                .then(function (response) {
                    console.log(response.data); // ex.: { user: 'Your User'}
                    console.log(response.status); // ex.: 200
                    console.log(self.username);
                    console.log("GETTING WORKOUTS");
                    self.workouts = response.data;
                }).catch(function (error) {
                console.log(error);
                console.log("NOT GETTING WORKOUTS");

            });
        }
    },
    mounted() {

        eventBus4.$on('userExercises', username => {
            var self = this;
            self.username = username;
            console.log("something is happening");
            self.getWorkouts();
            eventBus5.$emit('subscriptionEvent', self.username)
        });
        // self.getWorkouts();

    }
})

/*Vue.component('workout-details', {
    template: `
    <div class="row">
         <ul class="list-group ">
            <li class="list-group-item" v-for="exercise in exercise"><a href="#"class="float-left">Exercise: {{exercise.name}}</a></li>
            </ul>
</div>
    `,
    data: function () {
        return {
            exercises: [],
            username: '',

        }
    },
    mounted() {
        eventBus3.$on('workoutDetails', workout => {
            var self = this;
            // zemi gi site exercises
            // sets?? reps??

            axios.get('http://localhost:8080/exercises/' + workout.workout_date + '/' + self.username)
                .then(function (response) {
                    console.log(response.data); // ex.: { user: 'Your User'}
                    console.log(response.status); // ex.: 200
                    self.exercises = response.data;
                }).catch(function (error) {
                console.log(error);

            });
        })
    }
})*/


// END COMPONENTS FOR MY PROfiLE PAGE

var eventBus = new Vue()

var eventBus2 = new Vue()

var eventBus3 = new Vue()

var eventBus4 = new Vue()

var eventBus5 = new Vue()

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
    },
    mounted() {
    }
})