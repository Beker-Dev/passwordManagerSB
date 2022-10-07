<template>
    <div class="column">
      <div class="columns is-12 menu-top">
        Gerenciador de Senhas
      </div>
    </div>
  
    <div class="columns">
      <div class="column is-3">
        <nav>
          <aside class="menu">
            <p class="menu-label">Gerenciar Senhas</p>
            <ul class="menu-list">
              <li><button class="button is-white is-fullwidth" @click="onClickPasswordsPage()">Senhas</button></li>
            </ul>
            <p class="menu-label">Usuario</p>
            <ul class="menu-list">
              <li><button class="button is-white is-fullwidth" @click="onClickLogin()">Entrar</button></li>
              <li><button class="button is-white is-fullwidth" @click="onClickLogout()">Sair</button></li>
              <li><button class="button is-white is-fullwidth" @click="onClickRegister()">Registrar-se</button></li>
            </ul>
          </aside>
        </nav>
      </div>
      <div class="column is-9 conteudo">
        <router-view> </router-view>
      </div>
    </div>
  </template>
  
<script lang="ts">

  import { Vue } from 'vue-class-component';
  import { removeCookie } from 'typescript-cookie';
  import { AuthUtils } from '@/utils/AuthUtils';

  export default class SidebarUtils extends Vue {
    authUtils: AuthUtils = new AuthUtils()
    isAuthenticated!: boolean

    public mounted(): void {
        this.isAuthenticated = this.authUtils.checkAuthenticated()
    }

    private onClickLogout(): void {
      removeCookie("access")
      this.$router.push({ name: 'user-login' })
    }

    private onClickPasswordsPage(): void {
      this.$router.push({ name: 'password' })
    }

    private onClickLogin(): void {
      this.$router.push({ name: 'user-login' })
    }

    private onClickRegister(): void {
      this.$router.push({ name: 'user-register' })
    }

  }
</script>

  <style lang="scss">
  @import "~bulma/bulma.sass";
  #app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    color: #2c3e50;
  }
  nav {
    padding: 30px;
    a {
      font-weight: bold;
      color: #2c3e50;
      &.router-link-exact-active {
        color: #42b983;
      }
    }
  }
  .conteudo {
    margin-top: 30px;
  }
  .menu-top{
    background: hsl(0, 0%, 29%);
    padding: 15px;
    color: white;
  }
  .menu-label{
    font-size: 15px !important;
  }
  </style>