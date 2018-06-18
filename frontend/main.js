var app = new Vue({
    el: '#app',
    data: {
        loggedIn: false,
        user: {
            username: ""
        },
        users: [
            {
                username: "mare"
            }
        ],
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
        ]
    },
    methods: {
        fillDate: function () {
            this.news.date = "2"
        },
        logIn: function () {
            this.loggedIn = true,
                this.user = "Mare"
        },
        logOut: function () {
            this.loggedIn = false,
                this.user = ""
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
            }).done(function(val) {
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
