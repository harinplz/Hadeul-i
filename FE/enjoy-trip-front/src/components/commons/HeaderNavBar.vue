<template>
  <div class="shadow-sm p-3 mb-5 bg-white rounded">
    <!-- nav 끝 -->

    <b-navbar toggleable="lg" type="light">
      <b-navbar-brand href="#">EnjoyTrip!</b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item href="#">HOME</b-nav-item>
          <b-nav-item href="#">
            <router-link :to="{ name: 'attractions' }">Attraction</router-link>
          </b-nav-item>
          <b-nav-item href="#">HotPlace</b-nav-item>
          <b-nav-item href="#">Community</b-nav-item>
          <b-nav-item href="#">Notice</b-nav-item>
        </b-navbar-nav>

        <!-- Right aligned nav items -->
        <b-navbar-nav class="ml-auto">
          <!-- 로그인을 안 했을 시 -->
          <!-- <b-nav-item href="#" disabled>로그인이 필요합니다.</b-nav-item>

          <b-nav-item-dropdown right>
            <template #button-content>
              <em><b-icon icon="person" font-scale="1"></b-icon></em>
            </template>
            <b-dropdown-item href="#">로그인</b-dropdown-item>
            <b-dropdown-item href="#">회원가입</b-dropdown-item>
          </b-nav-item-dropdown> -->

          <!-- 관리자로 로그인했을 때 -->
          <b-nav-item href="#" disabled>관리자</b-nav-item>

          <b-nav-item-dropdown right>
            <template #button-content>
              <em><b-icon icon="person" font-scale="1"></b-icon></em>
            </template>
            <b-dropdown-item href="#">로그아웃</b-dropdown-item>
            <b-dropdown-item href="#">
              <router-link :to="{ name: 'admin' }">회원관리</router-link>
            </b-dropdown-item>
          </b-nav-item-dropdown>

          <!-- 관리자 외 회원 로그인 -->
          <!-- <b-nav-item href="#" disabled>님 환영합니다.</b-nav-item>

          <b-nav-item-dropdown right>
            <template #button-content>
              <em><b-icon icon="person" font-scale="1"></b-icon></em>
            </template>
            <b-dropdown-item href="#">로그아웃</b-dropdown-item>
            <b-dropdown-item href="#">마이페이지</b-dropdown-item>
          </b-nav-item-dropdown> -->
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>
