import {Routes} from '@angular/router';
import {${table.upperCaseName}Component} from './${table.lowerCaseSubName}.component';
import {${table.upperCaseName}DetailComponent} from './${table.lowerCaseSubName}-detail.component';
import {${table.upperCaseName}EditComponent} from './${table.lowerCaseSubName}-edit.component';

export const ${table.camelCaseName}Routes: Routes = [
    {
        path: '${table.lowerCaseSubName}',
        component: ${table.upperCaseName}InletComponent,
        children: [
            {
                path: '',
                component: ${table.upperCaseName}Component,
            }, {
                path: 'detail',
                component: ${table.upperCaseName}DetailComponent
            }, {
                path: 'new',
                component: ${table.upperCaseName}EditComponent
            }, {
                path: 'edit',
                component: ${table.upperCaseName}EditComponent
            }
        ]
    },

];

export const routed${table.upperCaseName}Components = [
    ${table.upperCaseName}InletComponent,
    ${table.upperCaseName}Component,
    ${table.upperCaseName}DetailComponent,
    ${table.upperCaseName}EditComponent,
];
