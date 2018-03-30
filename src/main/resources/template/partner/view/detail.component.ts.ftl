import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from 'rxjs/Subscription';
import {ActivatedRoute} from '@angular/router';
import {${table.upperCaseName}Service} from './${table.lowerCaseSubName}.service';

@Component({
    selector: 'ngx-${table.lowerCaseSubName}-detail',
    templateUrl: './${table.lowerCaseSubName}-detail.component.html'
})
export class ${table.upperCaseName}DetailComponent implements OnInit, OnDestroy {

    private subscription: Subscription;
    ${table.camelCaseName}: any;

    constructor(private ${table.camelCaseName}Service: ${table.upperCaseName}Service,
                private route: ActivatedRoute) {
    }

    ngOnInit(): void {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
    }

    load(id) {
        this.${table.camelCaseName}Service.detail(id).subscribe(response => {
            this.${table.camelCaseName} = response.body;
        })
    }

    ngOnDestroy(): void {
        this.subscription.unsubscribe();
    }

}
