"use strict";(self.webpackChunkfhircore=self.webpackChunkfhircore||[]).push([[969],{3905:(e,r,t)=>{t.d(r,{Zo:()=>l,kt:()=>d});var n=t(7294);function o(e,r,t){return r in e?Object.defineProperty(e,r,{value:t,enumerable:!0,configurable:!0,writable:!0}):e[r]=t,e}function a(e,r){var t=Object.keys(e);if(Object.getOwnPropertySymbols){var n=Object.getOwnPropertySymbols(e);r&&(n=n.filter((function(r){return Object.getOwnPropertyDescriptor(e,r).enumerable}))),t.push.apply(t,n)}return t}function i(e){for(var r=1;r<arguments.length;r++){var t=null!=arguments[r]?arguments[r]:{};r%2?a(Object(t),!0).forEach((function(r){o(e,r,t[r])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(t)):a(Object(t)).forEach((function(r){Object.defineProperty(e,r,Object.getOwnPropertyDescriptor(t,r))}))}return e}function p(e,r){if(null==e)return{};var t,n,o=function(e,r){if(null==e)return{};var t,n,o={},a=Object.keys(e);for(n=0;n<a.length;n++)t=a[n],r.indexOf(t)>=0||(o[t]=e[t]);return o}(e,r);if(Object.getOwnPropertySymbols){var a=Object.getOwnPropertySymbols(e);for(n=0;n<a.length;n++)t=a[n],r.indexOf(t)>=0||Object.prototype.propertyIsEnumerable.call(e,t)&&(o[t]=e[t])}return o}var c=n.createContext({}),s=function(e){var r=n.useContext(c),t=r;return e&&(t="function"==typeof e?e(r):i(i({},r),e)),t},l=function(e){var r=s(e.components);return n.createElement(c.Provider,{value:r},e.children)},u={inlineCode:"code",wrapper:function(e){var r=e.children;return n.createElement(n.Fragment,{},r)}},g=n.forwardRef((function(e,r){var t=e.components,o=e.mdxType,a=e.originalType,c=e.parentName,l=p(e,["components","mdxType","originalType","parentName"]),g=s(t),d=o,f=g["".concat(c,".").concat(d)]||g[d]||u[d]||a;return t?n.createElement(f,i(i({ref:r},l),{},{components:t})):n.createElement(f,i({ref:r},l))}));function d(e,r){var t=arguments,o=r&&r.mdxType;if("string"==typeof e||o){var a=t.length,i=new Array(a);i[0]=g;var p={};for(var c in r)hasOwnProperty.call(r,c)&&(p[c]=r[c]);p.originalType=e,p.mdxType="string"==typeof e?e:o,i[1]=p;for(var s=2;s<a;s++)i[s]=t[s];return n.createElement.apply(null,i)}return n.createElement.apply(null,t)}g.displayName="MDXCreateElement"},9134:(e,r,t)=>{t.r(r),t.d(r,{assets:()=>c,contentTitle:()=>i,default:()=>u,frontMatter:()=>a,metadata:()=>p,toc:()=>s});var n=t(7462),o=(t(7294),t(3905));const a={title:"Measure reporting"},i="Measure report configuration",p={unversionedId:"configurations/reporting/measure-report-config",id:"configurations/reporting/measure-report-config",title:"Measure reporting",description:"Any FHIRCore app can be configured to display reports. These configurations are used to configure the report indicators, which charts to use to render the reports, the interval for generating the reports etc.",source:"@site/docs/configurations/reporting/measure-report-config.mdx",sourceDirName:"configurations/reporting",slug:"/configurations/reporting/measure-report-config",permalink:"/configurations/reporting/measure-report-config",draft:!1,editUrl:"https://github.com/opensrp/fhircore/tree/main/docs/docs/configurations/reporting/measure-report-config.mdx",tags:[],version:"current",frontMatter:{title:"Measure reporting"},sidebar:"defaultSidebar",previous:{title:"In-app reporting",permalink:"/configurations/reporting/in-app-reporting"},next:{title:"Application",permalink:"/configurations/system-components/application-config"}},c={},s=[{value:"Sample JSON",id:"sample-json",level:2},{value:"Config properties",id:"config-properties",level:2}],l={toc:s};function u(e){let{components:r,...t}=e;return(0,o.kt)("wrapper",(0,n.Z)({},l,t,{components:r,mdxType:"MDXLayout"}),(0,o.kt)("h1",{id:"measure-report-configuration"},"Measure report configuration"),(0,o.kt)("p",null,"Any FHIRCore app can be configured to display reports. These configurations are used to configure the report indicators, which charts to use to render the reports, the interval for generating the reports etc."),(0,o.kt)("admonition",{type:"info"},(0,o.kt)("p",{parentName:"admonition"},"One application can have multiple configurations for measure report, each identified by a unique ",(0,o.kt)("inlineCode",{parentName:"p"},"id"),".")),(0,o.kt)("h2",{id:"sample-json"},"Sample JSON"),(0,o.kt)("pre",null,(0,o.kt)("code",{parentName:"pre",className:"language-json"},'{\n    "appId": "app",\n    "configType": "measureReport",\n    "registerId": "measureReport",\n    "reports": [\n        {\n            "id": "1",\n            "title": "Household Members",\n            "description": "Number of Households, Household members registered, disaggregated by age and gender for each age group",\n            "url": "http://fhir.org/guides/who/anc-cds/Measure/HOUSEHOLDIND01"\n        },\n        {\n            "id": "2",\n            "title": "Household Members ANC",\n            "description": "Number of Households, Household members enrolled into ANC, disaggregated by age",\n            "url": "http://fhir.org/guides/who/anc-cds/Measure/HOUSEHOLDPREGNANCY"\n        }\n    ]\n}\n')),(0,o.kt)("h2",{id:"config-properties"},"Config properties"),(0,o.kt)("table",null,(0,o.kt)("thead",{parentName:"table"},(0,o.kt)("tr",{parentName:"thead"},(0,o.kt)("th",{parentName:"tr",align:null},"Property"),(0,o.kt)("th",{parentName:"tr",align:null},"Description"),(0,o.kt)("th",{parentName:"tr",align:"center"},"Required"),(0,o.kt)("th",{parentName:"tr",align:"center"},"Default"))),(0,o.kt)("tbody",{parentName:"table"},(0,o.kt)("tr",{parentName:"tbody"},(0,o.kt)("td",{parentName:"tr",align:null},"appId"),(0,o.kt)("td",{parentName:"tr",align:null},"Unique identifier for the application"),(0,o.kt)("td",{parentName:"tr",align:"center"},"Yes"),(0,o.kt)("td",{parentName:"tr",align:"center"})),(0,o.kt)("tr",{parentName:"tbody"},(0,o.kt)("td",{parentName:"tr",align:null},"configType"),(0,o.kt)("td",{parentName:"tr",align:null},"Type of configuration"),(0,o.kt)("td",{parentName:"tr",align:"center"},"Yes"),(0,o.kt)("td",{parentName:"tr",align:"center"},(0,o.kt)("inlineCode",{parentName:"td"},"measureReport"))))))}u.isMDXComponent=!0}}]);