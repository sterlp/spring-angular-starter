export interface Person {
    id?: number;
    name?: string;
    address?: string;
    version?: number;
}

// tslint:disable: curly
export class PersonModel {
    static readonly COLUMNS = [
        { id: 'name',       label: 'Name',    cell: (e: Person) => e.name      },
        { id: 'address',    label: 'Address', cell: (e: Person) => e.address   }
    ];
    static getLabel(val: string | any) {
        const result = PersonModel.COLUMNS.filter(c => c.id === val)[0];
        if (result) return result.label;
        else return null;
    }
}
