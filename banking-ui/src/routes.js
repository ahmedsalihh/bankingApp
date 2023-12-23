import AddUser from './containers/AddUser';
import ListCreditInstallments from './containers/ListCreditInstallments';
import ListUsers from './containers/ListUser';
import ListUserCredits from './containers/ListUserCredits';

export const routes = [
  {
    path: '/',
    title: 'List User',
    visible: true,
    component: <ListUsers />,
  },
  {
    path: '/addUser',
    title: 'Add User',
    visible: true,
    component: <AddUser />,
  },
  {
    path: '/listCredits/:id',
    title: 'List User Credits',
    visible: false,
    component: <ListUserCredits />,
  },
  {
    path: '/listCreditInstallments/:creditId',
    title: 'List Credit Installments',
    visible: false,
    component: <ListCreditInstallments />,
  },
];
