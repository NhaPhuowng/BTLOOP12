# Bomberman - OOP_N4_BTL_N12
<p float="left">
  <img src="https://user-images.githubusercontent.com/100220743/198242750-b1c0968b-a577-4359-999d-050562dcf3a8.png" width="500" />
  <img src="https://user-images.githubusercontent.com/100220743/198200769-411f83f6-e7ea-44a1-afe0-709a87177f68.png" width="500" /> 
</p>

#### Thành viên:
|STT|Họ và tên|MSSV|Email|
|---|---------|----|-----|
|1|Lê Minh Thuận|21020409|21020409@vnu.edu.vn|
|2|Trần Xuân Trường|21020418|21020418@vnu.edu.vn|
|3|Phan Thị Nhã Phương|21020379|21020379@vnu.edu.vn|

#### Map 🗺 :
* !: Player
* 1: Portal
* 2: Wall
* 3: Brick
* 4: SpeedItem
* 5: FlamesItem
* 6: BombsItem
* 7: BrickPassItem
* 8: BombPassItem
* B: Balloom
* O: Oneal
* D: Doll
* K: Kondoria
* N: Nuclear
* M: Minvo

#### Mô tả các đối tượng 💣 :
- ![player_down](https://user-images.githubusercontent.com/100220743/198201305-e91789e6-18ed-40d5-bc71-6320e661297f.png) Nhân vật:
  - Di chuyển bằng W, A, S, D hoặc các phím mũi tên.
  - Đặt bom bằng SPACE.

- Kẻ thù:
    - ![balloom_left1](https://user-images.githubusercontent.com/100220743/198201007-ee90c2eb-e861-458b-b9e4-a2e3badd0c06.png)
 Balloom: di chuyển chậm, ngẫu nhiên.
    - ![oneal_left1](https://user-images.githubusercontent.com/100220743/198201075-ce988251-5f4c-4c34-8f01-b56b8859a81a.png)
 Oneal: di chuyển chậm, đuổi theo nhân vật khi ở khoảng cách gần.
    - ![doll_left1](https://user-images.githubusercontent.com/100220743/198201168-9879260f-1d81-4112-84ff-62a85a4428c8.png)
 Doll: di chuyển nhanh, ngẫu nhiên (ưu tiên sang hai bên).
    - ![kondoria_left1](https://user-images.githubusercontent.com/100220743/198201198-6fcff410-b917-4d0b-84f9-407e5b786bdc.png)
 Kondoria: di chuyển cực chậm, có khả năng đi xuyên gạch, đuổi theo nhân vật khi ở khoảng cách trung bình.
    - ![nuclear](https://user-images.githubusercontent.com/100220743/198201233-cb9fd434-09e3-4f23-837a-576b0058a835.png) Nuclear: di chuyển lúc nhanh lúc chậm, phát nổ khi nhân vật ở gần (kể cả bị chặn bởi block).
    - ![minvo_left1](https://user-images.githubusercontent.com/100220743/198201257-b9d9ea19-c223-4757-b525-d1e69dfc40b0.png) Minvo: di chuyển cực nhanh, có khả năng xuyên gạch, đuổi theo nhân vật ở khoảng cách xa, là kẻ thù nguy hiểm nhất.

- Item:
  - ![powerup_speed](https://user-images.githubusercontent.com/100220743/198201390-1564e3f8-05cc-4d98-9163-98b1d94ae7bb.png) SpeedItem: tăng tốc độ của nhân vật.
  - ![powerup_flames](https://user-images.githubusercontent.com/100220743/198201423-41459e01-3233-44b4-a854-95b6b5e46406.png) FlamesItem: tăng bán kính nổ.
  - ![powerup_bombs](https://user-images.githubusercontent.com/100220743/198201451-51193d73-6877-44af-9c41-0aacaff5223c.png) BombsItem: tăng số bom có thể đặt cùng lúc.
  - ![powerup_wallpass](https://user-images.githubusercontent.com/100220743/198201482-9667bdae-8ed3-4e18-9a88-a82a2db8e43b.png) BrickPassItem: giúp nhân vật có khả năng đi xuyên tường.
  - ![powerup_bombpass](https://user-images.githubusercontent.com/100220743/198201517-25f61742-edbb-4bb8-aad4-fe5b2af3afd4.png) BombPassItem: giúp nhân vật có khả năng đi xuyên bom.
