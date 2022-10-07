<template>
    <div class="container">
        <div class="columns" v-if="notification.ativo">
            <div class="column is-12">
                <div :class="notification.classe">
                    <button @click="onClickCloseNotification()" class="delete"></button>
                    {{ notification.mensagem }}
                </div>
            </div>
        </div>

        <div class="field">
            <p class="control has-icons-left has-icons-right">
                <input class="input" type="text" placeholder="Usuario" v-model="user.username">
                <span class="icon is-small is-left">
                    <i class="fas fa-envelope"></i>
                </span>
                <span class="icon is-small is-right">
                    <i class="fas fa-check"></i>
                </span>
            </p>
        </div>
        <div class="field">
            <p class="control has-icons-left">
                <input class="input" type="password" placeholder="Senha" v-model="user.password">
                <span class="icon is-small is-left">
                    <i class="fas fa-lock"></i>
                </span>
            </p>
        </div>
        <div class="field">
            <p class="control has-icons-left">
                <input class="input" type="password" placeholder="Confirmar Senha" v-model="confirmPw">
                <span class="icon is-small is-left">
                    <i class="fas fa-lock"></i>
                </span>
            </p>
        </div>
        <div class="field">
            <p class="control">
                <div class="buttons">
                <button class="button is-link" @click="onClickRegister()">
                    Registrar
                </button>
                </div>
            </p>
        </div>
    </div>
</template>

<script lang="ts">
import { Vue } from 'vue-class-component';
import { Prop } from 'vue-property-decorator';
import { Notification } from '@/model/Notification'
import { User } from '@/model/UserModel';
import { UserClient } from '@/client/UserClient';
import { setCookie, getCookie, removeCookie } from "typescript-cookie";
import { AuthUtils } from '@/utils/AuthUtils';

export default class UserRegister extends Vue {
    private notification: Notification = new Notification()
    private userClient!: UserClient
    private user: User = new User()
    private authUtils: AuthUtils = new AuthUtils()
    private confirmPw: String

    public mounted(): void {
        this.redirectPage()
        this.userClient = new UserClient()
    }

    public redirectPage(): void {
        var authenticated = this.authUtils.checkAuthenticated()
        if (authenticated) {
            this.$router.push({ name: 'password' })
        }
    }

    private onClickCloseNotification(): void {
        this.notification = new Notification()
    }

    private onClickRegister(): void {
        if (this.checkPw()) {
            this.userClient.save(this.user)
            .then(
                success => {
                    this.user = success.data
                    this.notification = this.notification.new(true, 'notification is-success', 'Usuario registrado com sucesso!')
                    this.saveCredentials()
                    this.onClickClean()
                }, error => {
                    this.notification = this.notification.new(true, 'notification is-danger', 'Erro ao registrar ' + error)
                    this.onClickClean()
                }
            )
        }
    }

    private checkPw(): boolean {
        if (this.user.password != this.confirmPw) {
            this.notification = this.notification.new(true, 'notification is-danger', 'Senhas nao conferem')
            this.onClickClean()
            return false
        }
        return true
    }

    private onClickClean(): void {
        this.user = new User()
    }

    private saveCredentials(): void {
        removeCookie("access")
        setCookie("access", this.user.id, {expires: 1})
    }
}
</script>

<style lang="scss">
@import "~bulma/bulma.sass";

.container {
    margin-left: 30px;
    width: 80%;
}

.titulo {
    display: flex;
    justify-content: center;
    font-size: 30px;
}

.enums {
    display: flex;
    flex-direction: row;
    justify-content: flex-start;
    margin-block-end: 20px;
}

.enum-tipo-atendimento {
    margin-left: 40px;
}

.botoes-form {
    display: flex;
    justify-content: space-between;
    flex-direction: row;
    width: 100%;
    margin-top: 20px;
    margin-block-end: 20px;
}

.btn-salvar {
    background-color: green;
    color: white;
    width: 40%;
}

.link-cad {
    width: 40%;
}

.btn-voltar {
    background-color: red;
    color: white;
    width: 100%;
}
</style>