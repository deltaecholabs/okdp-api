import { Component, OnDestroy, OnInit } from "@angular/core";

@Component({ template: '' })
export abstract class BaseComponent implements OnInit, OnDestroy {

  errors?: Error[];

  abstract init(): void;

  abstract destroy(): void;

  ngOnInit(): void {
    this.init();
  }

  ngOnDestroy(): void {
    this.destroy();
  }

}
