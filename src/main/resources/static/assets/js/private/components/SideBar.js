export default {
    name: 'SideBar',
    template:
    /* html */
    `
        <div class="d-flex flex-column flex-shrink-0 p-3 text-dark bg-light" style="min-height: 100vh;">
            <router-link to="/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
              <svg class="bi me-2" width="40" height="32"><use xlink:href="#bootstrap"/></svg>
              <span class="fs-4">Profile</span>
            </router-link>
            <hr>
            <ul class="nav nav-pills flex-column mb-auto">
            <li class="list-group mb-2">
                <a href="/index" class="list-group-item list-group-item-action bg-light text-dark">
                    <svg class="bi me-2" width="16" height="16"></svg>
                    Homepage
                </a> 
              </li>
              <li class="list-group mb-2">
                <router-link to="/info" class="list-group-item list-group-item-action bg-light text-dark">
                    <svg class="bi me-2" width="16" height="16"></svg>
                    Detail Info
                </router-link> 
              </li>
              <li class="list-group mb-2">
                <router-link to="/password" class="list-group-item list-group-item-action bg-light text-dark">
                    <svg class="bi me-2" width="16" height="16"></svg>
                    Password Settings
                </router-link> 
              </li>
              <li class="list-group mb-2">
                <router-link to="/bought-item" class="list-group-item list-group-item-action bg-light text-dark">
                    <svg class="bi me-2" width="16" height="16"></svg>
                    Bought Items
                </router-link> 
              </li>
              <li class="list-group mb-2">
                <a href="/auth/logout" class="list-group-item list-group-item-action btn-outline-danger text-danger">
                    <svg class="bi me-2" width="16" height="16"></svg>
                    Log Out
                </a> 
              </li>
            </ul>
            <hr>
        </div>
    `
};