/**
 * @see https://vuepress.vuejs.org/zh/
 */
module.exports = {
  port: '4000',
  dest: 'dist',
  base: '/java-tutorial/',
  title: 'JAVA-TUTORIAL',
  description: 'Java 教程',
  head: [['link', { rel: 'icon', href: `/favicon.ico` }]],
  markdown: {
    externalLinks: {
      target: '_blank',
      rel: 'noopener noreferrer',
    },
  },
  themeConfig: {
    logo: 'https://raw.githubusercontent.com/dunwu/images/dev/common/dunwu-logo-200.png',
    repo: 'dunwu/java-tutorial',
    repoLabel: 'Github',
    docsDir: 'docs',
    docsBranch: 'master',
    editLinks: true,
    smoothScroll: true,
    locales: {
      '/': {
        label: '简体中文',
        selectText: 'Languages',
        editLinkText: '帮助我们改善此页面！',
        lastUpdated: '上次更新',
        nav: [
          {
            text: 'JavaEE',
            link: '/javaee/',
          },
          {
            text: '框架',
            link: '/framework/',
          },
          {
            text: '缓存',
            link: '/cache/',
          },
          {
            text: '消息队列',
            link: '/mq/',
          },
          {
            text: 'LIB库',
            link: '/lib/',
          },
          {
            text: '微服务',
            link: '/microservice/',
          },
          {
            text: '安全',
            link: '/security/',
          },
          {
            text: '测试',
            link: '/test/',
          },
          {
            text: '服务器',
            link: '/server/',
          },
          {
            text: '工具',
            link: '/tool/',
            items: [
              {
                text: '构建',
                link: '/tool/build/',
              },
              {
                text: 'IDE',
                link: '/tool/ide/',
              },
              {
                text: '监控',
                link: '/tool/monitor/',
              },
            ],
          },
          {
            text: '✨ Java系列',
            ariaLabel: 'Java',
            items: [
              {
                text: 'Java 教程 📚',
                link: 'https://dunwu.github.io/java-tutorial/',
                target: '_blank',
                rel: '',
              },
              {
                text: 'JavaCore 教程 📚',
                link: 'https://dunwu.github.io/javacore/',
                target: '_blank',
                rel: '',
              },
              {
                text: 'Spring 教程 📚',
                link: 'https://dunwu.github.io/spring-tutorial/',
                target: '_blank',
                rel: '',
              },
              {
                text: 'Spring Boot 教程 📚',
                link: 'https://dunwu.github.io/spring-boot-tutorial/',
                target: '_blank',
                rel: '',
              },
            ],
          },
          {
            text: '🎯 博客',
            link: 'https://github.com/dunwu/blog',
            target: '_blank',
            rel: '',
          },
        ],
        sidebar: 'auto',
        sidebarDepth: 2,
      },
    },
  },
  plugins: [
    [
      '@vuepress/active-header-links',
      {
        sidebarLinkSelector: '.sidebar-link',
        headerAnchorSelector: '.header-anchor',
      },
    ],
    ['@vuepress/back-to-top', true],
    [
      '@vuepress/pwa',
      {
        serviceWorker: true,
        updatePopup: true,
      },
    ],
    [
      '@vuepress/last-updated',
      {
        transformer: (timestamp, lang) => {
          // 不要忘了安装 moment
          const moment = require('moment')
          moment.locale(lang)
          return moment(timestamp).fromNow()
        },
      },
    ],
    ['@vuepress/medium-zoom', true],
    [
      'container',
      {
        type: 'vue',
        before: '<pre class="vue-container"><code>',
        after: '</code></pre>',
      },
    ],
    [
      'container',
      {
        type: 'upgrade',
        before: (info) => `<UpgradePath title="${info}">`,
        after: '</UpgradePath>',
      },
    ],
    ['flowchart'],
  ],
}
