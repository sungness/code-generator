import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from 'rxjs/Subscription';
import {ActivatedRoute, Router} from '@angular/router';
import {${table.upperCaseName}Service} from './${table.lowerCaseSubName}.service';

@Component({
    selector: 'ngx-${table.lowerCaseSubName}-edit',
    templateUrl: './${table.lowerCaseSubName}-edit.component.html',
})
export class ${table.upperCaseName}EditComponent implements OnInit, OnDestroy {

    private subscription: Subscription;
    ${table.camelCaseName}: any;
    submitting: boolean = false;

    constructor(private ${table.camelCaseName}Service: ${table.upperCaseName}Service,
                protected router: Router,
                private route: ActivatedRoute) {
    }

    ngOnInit(): void {
        this.subscription = this.route.params.subscribe((params) => {
            params['id'] ? this.load(params['id']) : (this.${table.camelCaseName} = {});
        });
    }

    load(id) {
        this.${table.camelCaseName}Service.detail(id).subscribe(response => {
            this.${table.camelCaseName} = response.body;
        })
    }

    save() {
        this.submitting = true;

        if (this.${table.camelCaseName}.id) {
            this.${table.camelCaseName}Service.update(this.${table.camelCaseName}).subscribe(res => this.submitting = false);
        } else {
            this.${table.camelCaseName}Service.create(this.${table.camelCaseName}).subscribe((response) => {
                this.router.navigate(['../detail', {id: response.body.id}],
                                    {relativeTo: this.route}).then(() => {});
            })
        }

    }

    ngOnDestroy(): void {
        this.subscription.unsubscribe();
    }

}
