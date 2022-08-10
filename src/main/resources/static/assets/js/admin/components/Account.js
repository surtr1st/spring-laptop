const REST_URL = "http://localhost:8080/rest"

export default {
    name: 'Account',
    data() {
        return {
            authority: []
        }
    },
    methods: {
        onFetchAuthority() {
            fetch(`${REST_URL}/authorities`)
                .then(async res => {
                    this.authority = await res.json()
                })
                .catch(e => console.error(e))
        },

        async onUpdate(id, role) {
            const newRole = (role === 'ADMIN') ? 'USER' : 'ADMIN'

            const patch = {
                method: 'PATCH',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: newRole
            }
            await fetch(`${REST_URL}/update-role/${id}`, patch)
            await this.reRender()
        },

        async onDelete(id) {
            await fetch(`${REST_URL}/delete-account/${id}`, {
                method: 'DELETE'
            })
            await this.reRender()
        },

        onReset() {
            this.account = {}
        },

        async reRender() {
            await Vue.nextTick()
            this.onFetchAuthority()
        },
    },
    created() {
        this.onFetchAuthority()
    },
    template: /* html */
        `
    <div class="row col-xl-10 col-lg-10 p-5">
		<table class="table table-hover mb-0 bg-white rounded rounded-5">
			<thead class="bg-primary text-white text-center">
				<tr>
					<th>Id</th>
					<th>Username</th>
					<th>Role</th>
					<th>Change Role</th>
					<th>Custom</th>
				</tr>
			</thead>
			<tbody class="text-center align-middle">
			    <tr v-for="(auth, index) in authority" :key="index">
			        <td>
			            {{ auth.id }}
			        </td>
                    <td>{{ auth.account.username }}</td>
                    <td>{{ auth.rid }}</td>
                    <td>
                        <div class="form-check">
                            <input 
                                id="role"
                                type="checkbox"
                                :checked="(auth.rid === 'ADMIN') ? true : false"
                                @click="onUpdate(auth.id, auth.rid)"
                                class="form-check-input align-self-center"
                            />
                        </div>
                    </td>
                    <td>
                        <a @click="onDelete(auth.id)" class="btn btn-outline-danger">Remove</a>
                    </td>
                </tr> 
			</tbody>
        </table>
    </div>
    `
};