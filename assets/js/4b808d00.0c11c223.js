"use strict";(self.webpackChunkfhircore=self.webpackChunkfhircore||[]).push([[203],{3905:(e,t,n)=>{n.d(t,{Zo:()=>c,kt:()=>m});var r=n(7294);function o(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}function a(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)}return n}function i(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?a(Object(n),!0).forEach((function(t){o(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):a(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function l(e,t){if(null==e)return{};var n,r,o=function(e,t){if(null==e)return{};var n,r,o={},a=Object.keys(e);for(r=0;r<a.length;r++)n=a[r],t.indexOf(n)>=0||(o[n]=e[n]);return o}(e,t);if(Object.getOwnPropertySymbols){var a=Object.getOwnPropertySymbols(e);for(r=0;r<a.length;r++)n=a[r],t.indexOf(n)>=0||Object.prototype.propertyIsEnumerable.call(e,n)&&(o[n]=e[n])}return o}var p=r.createContext({}),s=function(e){var t=r.useContext(p),n=t;return e&&(n="function"==typeof e?e(t):i(i({},t),e)),n},c=function(e){var t=s(e.components);return r.createElement(p.Provider,{value:t},e.children)},u={inlineCode:"code",wrapper:function(e){var t=e.children;return r.createElement(r.Fragment,{},t)}},d=r.forwardRef((function(e,t){var n=e.components,o=e.mdxType,a=e.originalType,p=e.parentName,c=l(e,["components","mdxType","originalType","parentName"]),d=s(n),m=o,f=d["".concat(p,".").concat(m)]||d[m]||u[m]||a;return n?r.createElement(f,i(i({ref:t},c),{},{components:n})):r.createElement(f,i({ref:t},c))}));function m(e,t){var n=arguments,o=t&&t.mdxType;if("string"==typeof e||o){var a=n.length,i=new Array(a);i[0]=d;var l={};for(var p in t)hasOwnProperty.call(t,p)&&(l[p]=t[p]);l.originalType=e,l.mdxType="string"==typeof e?e:o,i[1]=l;for(var s=2;s<a;s++)i[s]=n[s];return r.createElement.apply(null,i)}return r.createElement.apply(null,n)}d.displayName="MDXCreateElement"},6568:(e,t,n)=>{n.r(t),n.d(t,{assets:()=>p,contentTitle:()=>i,default:()=>u,frontMatter:()=>a,metadata:()=>l,toc:()=>s});var r=n(7462),o=(n(7294),n(3905));const a={},i="Sentry setup",l={unversionedId:"developer-setup/sentry",id:"developer-setup/sentry",title:"Sentry setup",description:"### Note",source:"@site/docs/developer-setup/sentry.mdx",sourceDirName:"developer-setup",slug:"/developer-setup/sentry",permalink:"/developer-setup/sentry",draft:!1,editUrl:"https://github.com/opensrp/fhircore/tree/main/docs/docs/developer-setup/sentry.mdx",tags:[],version:"current",frontMatter:{},sidebar:"defaultSidebar",previous:{title:"Publishing FHIR SDK Library Artifacts",permalink:"/developer-setup/publishing-fhir-sdk-artifacts"},next:{title:"Writing FHIR",permalink:"/writing-fhir/"}},p={},s=[{value:"Instructions",id:"instructions",level:3}],c={toc:s};function u(e){let{components:t,...n}=e;return(0,o.kt)("wrapper",(0,r.Z)({},c,n,{components:t,mdxType:"MDXLayout"}),(0,o.kt)("h1",{id:"sentry-setup"},"Sentry setup"),(0,o.kt)("blockquote",null,(0,o.kt)("h3",{parentName:"blockquote",id:"note"},"Note"),(0,o.kt)("ul",{parentName:"blockquote"},(0,o.kt)("li",{parentName:"ul"},"You need to have already setup a ",(0,o.kt)("a",{parentName:"li",href:"https://docs.sentry.io/product/sentry-basics/integrate-frontend/create-new-project/"},"project")," and dashboard with the necessary access permissions, and"),(0,o.kt)("li",{parentName:"ul"},"are able to obtain the dsn of this project through\xa0",(0,o.kt)("inlineCode",{parentName:"li"},"[Project]\xa0> Settings > Client Keys (DSN)"),". This will be required later for configuration and logging."))),(0,o.kt)("h3",{id:"instructions"},"Instructions"),(0,o.kt)("ol",null,(0,o.kt)("li",{parentName:"ol"},(0,o.kt)("p",{parentName:"li"},"Once you have created the Sentry project as instructed above, inside the ",(0,o.kt)("inlineCode",{parentName:"p"},"local.properties")," file add a line in the form\n",(0,o.kt)("inlineCode",{parentName:"p"},"sentry.dsn=xxxxxx")," replace the ",(0,o.kt)("inlineCode",{parentName:"p"},"xxxxxx")," with the ",(0,o.kt)("inlineCode",{parentName:"p"},"sentry dsn")," from your project in sentry. This will be picked up\nautomatically by the setup inside the quest module's build.gradle file")),(0,o.kt)("li",{parentName:"ol"},(0,o.kt)("p",{parentName:"li"},"If you want to seperate your staging and production environment logs and performance information you can add an environment variable\nto the Sentry,init method. You will need to insert an extra option in the form of ",(0,o.kt)("inlineCode",{parentName:"p"},"options.environment = <Your Environment Name>"),". When\ndata gets pushed to the dashboard for the first time, the environment will be automatically created. Use the\xa0Environment tag set from the previous step to filter               environment as shown below.\n",(0,o.kt)("img",{parentName:"p",src:"https://github.com/opensrp/fhircore/assets/12864384/e00e9da9-01ea-41ec-aa72-5588fde4fdf8",alt:"sentry_environment_dropdown"}))),(0,o.kt)("li",{parentName:"ol"},(0,o.kt)("p",{parentName:"li"},"Timber logs should show up in the Sentry dashboard. Sentry will track crashes and timber Errors. You can also\nconfigure the log levels inside the Sentry.init function")),(0,o.kt)("li",{parentName:"ol"},(0,o.kt)("p",{parentName:"li"},"By default we have configured additional sentry integrations for:"),(0,o.kt)("ul",{parentName:"li"},(0,o.kt)("li",{parentName:"ul"},"Navigation"),(0,o.kt)("li",{parentName:"ul"},"Fragments")),(0,o.kt)("p",{parentName:"li"},"You can also add support for ",(0,o.kt)("inlineCode",{parentName:"p"},"Jetpack compose")," and ",(0,o.kt)("inlineCode",{parentName:"p"},"OkHttp")))))}u.isMDXComponent=!0}}]);