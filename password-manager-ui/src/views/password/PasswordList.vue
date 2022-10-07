<template>
    <div class="columns">
      <div class="column is-12 is-size-3">
        Lista de Senhas
      <button @click="onClickRegisterPage()" class="button is-success">Cadastrar</button>
      </div>
    </div>
  
    <hr />
  
    <table class="table table is-fullwidth">
      <thead class="green">
      <tr style="background-color: hsl(0, 0%, 29%)">
        <th style="color: #fff;">ID</th>
        <th style="color: #fff;">Nome</th>
        <th style="color: #fff;">Descrição</th>
        <th style="color: #fff;">URL</th>
        <th style="color: #fff;">Senha</th>
        <th style="color: #fff;">Opções</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="item in passwordList" :key="item.id">
        <th>{{ item.id }}</th>  
        <th>{{ item.name }}</th>
        <th>{{ item.description }}</th>
        <th>{{ item.url }}</th>
        <th>{{ item.password }}</th>
        <th>
          <button @click="onClickDetailPage(item.id)" class="button is-link">Detalhar</button>
        </th>
      </tr>
      </tbody>
    </table>
    <pagination />
  </template>
  
  <script lang="ts">
  import { Vue, Options } from 'vue-class-component';
  import { PageRequest } from '@/model/page/page-request'
  import { PageResponse } from '@/model/page/page-response'
  import { Password } from '@/model/PasswordModel';
  import { PasswordClient } from '@/client/PasswordClient';
  import { Pagination } from '@/components/Pagination.vue';

  @Options({
    components: {
      Pagination
    }
  })

  export default class PasswordList extends Vue {
    private pageRequest: PageRequest = new PageRequest()
    private pageResponse: PageResponse<Password> = new PageResponse()
    private passwordList: Password[] = []
    private passwordClient!: PasswordClient

    public mounted(): void {
      this.passwordClient = new PasswordClient()
      this.listPw()
    }

    private listPw(): void {
      this.passwordClient.findAll(this.pageRequest)
          .then(
              success => {
                this.pageResponse = success
                this.passwordList = this.pageResponse.content
              },
              error => console.log(error)
          )
    }

    private onClickDetailPage(id: number): void {
      this.$router.push({ name: 'password-detail', params: { id: id } })
    }

    private onClickRegisterPage(): void {
      this.$router.push({ name: 'password-register' })
    }
  }
  </script>
