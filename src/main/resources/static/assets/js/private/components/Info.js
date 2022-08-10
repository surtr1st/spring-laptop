const REST_URL = 'http://localhost:8080/rest'

export default {
    name: 'Info',
    data() {
        return {
            src: '/assets/images/profile/',
            uid: '',
            account: {
                username: '',
                password: '',
                email: '',
                image: ''
            },
        }
    },
    methods: {
        onUpload() {
            const upload = document.getElementById('upload-photo')
            const img = document.getElementById('img')
            const btnChange = document.getElementById('change-image')
            const [file] = upload.files

            if (file) {
                img.src = URL.createObjectURL(file)
                btnChange.removeAttribute('disabled')
            }
        },

        async getImageFile() {
            const upload = document.getElementById('upload-photo')
            const data = new FormData()
            data.append('file', upload.files[0])

            await axios.post(`${REST_URL}/upload/images/profile`, data, {
                headers: {
                    'Content-Type': 'multipart/form-data; boundary=none'
                }
            }).then(res => this.account.image = upload.files[0].name)
        },
        onFetchInfo() {
            fetch(`${REST_URL}/info`)
                .then(async res => {
                    this.account = await res.json()
                })
                .catch(e => console.log(e))
            fetch(`${REST_URL}/uid`)
                .then(async res => {
                    this.uid = await res.text()
                })
                .catch(e => console.log(e))
        },

        async onUpdateImage() {
            await this.getImageFile()

            const patch = {
                method: 'PATCH',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: this.account.image
            }
            await fetch(`${REST_URL}/info/update-image/${this.uid}`, patch)
            await this.reRender()
        },

        async reRender() {
            await Vue.nextTick()
            this.onFetchInfo()
        }
    },
    created() {
        this.onFetchInfo()
    },
    destroyed() {
        this.uid = ''
        this.account = {}
    },
    template: /*html*/
    `
        <div class="row col-xl-10 col-lg-10 p-5">
            <h1 class="text-center text-primary fw-bolder">YOUR PROFILE</h1>
            <div class="col-lg-6 d-flex justify-content-center align-items-center">
              <form class="row" method="post" v-on:submit.prevent enctype="multipart/form-data">
                <div class="col-md-12 mb-5">
                  <label for="04" class="form-label">Username</label>
                  <div class="input-group">
                    <input v-model="account.username" type="text" class="form-control" id="04" readonly>
                  </div>
                </div>
                <div class="col-md-12 mb-5">
                  <label for="05" class="form-label">Password</label>
                  <div class="input-group">
                    <input v-model="account.password" type="password" class="form-control" id="05" readonly>
                  </div>
                </div>
                <div class="col-md-12 mb-5">
                  <label for="06" class="form-label">Email</label>
                  <div class="input-group">
                    <input v-model="account.email" type="text" class="form-control" id="06" readonly>
                  </div>
                </div>
                <div class="form-outline col-md-12">
				    <label for="prodImg">Image</label>
					<div class="input-group mb-3">
						<input
                            name="file"
                            type="file" 
                            @change="onUpload"
						    id="upload-photo"
                            class="form-control file"
                            accept="image/*">
					</div>
				</div>
				<div class="form-outline text-center">
					<button id="change-image" disabled @click="onUpdateImage()" class="btn btn-lg btn-success m-2">Change Image</button>
				</div>
              </form>
            </div>
            <div class="col-lg-6">
                <div
				    class="bg-white border rounded d-flex justify-content-center align-items-center h-100"
					style="height: 380px;">
					<img :src="src + account.image" class="img-fluid rounded" id="img" style="width: 60%;">
			    </div>
            </div>
		</div>
    </div>
    `
}